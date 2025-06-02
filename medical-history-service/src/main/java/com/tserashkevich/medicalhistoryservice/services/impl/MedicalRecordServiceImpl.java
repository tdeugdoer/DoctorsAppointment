package com.tserashkevich.medicalhistoryservice.services.impl;

import com.tserashkevich.medicalhistoryservice.dtos.FindAllParams;
import com.tserashkevich.medicalhistoryservice.dtos.MedicalRecordRequest;
import com.tserashkevich.medicalhistoryservice.dtos.MedicalRecordResponse;
import com.tserashkevich.medicalhistoryservice.dtos.PageResponse;
import com.tserashkevich.medicalhistoryservice.dtos.UpdateMedicalRecordRequest;
import com.tserashkevich.medicalhistoryservice.dtos.feign.AppointmentResponse;
import com.tserashkevich.medicalhistoryservice.exceptions.AppointmentNotFoundException;
import com.tserashkevich.medicalhistoryservice.exceptions.MedicalRecordMissingFileKeyException;
import com.tserashkevich.medicalhistoryservice.exceptions.MedicalRecordNotFoundException;
import com.tserashkevich.medicalhistoryservice.exceptions.feign.OtherServiceNotFoundException;
import com.tserashkevich.medicalhistoryservice.feign.AppointmentFeignClient;
import com.tserashkevich.medicalhistoryservice.mappers.MedicalRecordMapper;
import com.tserashkevich.medicalhistoryservice.models.MedicalRecord;
import com.tserashkevich.medicalhistoryservice.repositories.MedicalRecordRepository;
import com.tserashkevich.medicalhistoryservice.services.FileService;
import com.tserashkevich.medicalhistoryservice.services.MedicalRecordService;
import com.tserashkevich.medicalhistoryservice.utils.LogList;
import com.tserashkevich.medicalhistoryservice.utils.QueryPredicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final MongoTemplate mongoTemplate;
    private final MedicalRecordMapper medicalRecordMapper;
    private final FileService fileService;
    private final AppointmentFeignClient appointmentFeignClient;

    @Override
    public MedicalRecordResponse create(MedicalRecordRequest medicalRecordRequest, List<MultipartFile> files) {
        MedicalRecord medicalRecord = medicalRecordMapper.toModel(medicalRecordRequest);
        AppointmentResponse appointmentResponse = getAppointment(medicalRecord.getAppointment());

        medicalRecordMapper.updateModel(medicalRecord, appointmentResponse);
        if (files != null) {
            medicalRecord.setFileKeys(fileService.upload(files));
        }

        medicalRecordRepository.save(medicalRecord);

        log.info(LogList.CREATE_MEDICAL_RECORD, medicalRecord.getId());
        return medicalRecordMapper.toResponse(medicalRecord);
    }

    @Override
    public MedicalRecordResponse update(String medicalRecordId,
                                        UpdateMedicalRecordRequest updateMedicalRecordRequest,
                                        List<MultipartFile> files) {
        MedicalRecord medicalRecord = getOrThrow(medicalRecordId);

        medicalRecordMapper.updateModel(medicalRecord, updateMedicalRecordRequest);
        if (files != null) {
            medicalRecord.addFileKeys(fileService.upload(files));
        }

        medicalRecordRepository.save(medicalRecord);
        log.info(LogList.EDIT_MEDICAL_RECORD, medicalRecordId);

        return medicalRecordMapper.toResponse(medicalRecord);
    }

    @Override
    public void delete(String medicalHistoryId) {
        MedicalRecord medicalRecord = getOrThrow(medicalHistoryId);

        medicalRecordRepository.delete(medicalRecord);
        fileService.delete(medicalRecord.getFileKeys());

        log.info(LogList.DELETE_MEDICAL_RECORD);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<MedicalRecordResponse> findAll(FindAllParams findAllParams) {
        Pageable pageable = PageRequest.of(findAllParams.getPage(), findAllParams.getLimit(), findAllParams.getSort());
        Query query = QueryPredicate.builder()
                .add(findAllParams.getPatient(), Criteria.where("patient").is(findAllParams.getPatient()))
                .add(findAllParams.getAppointment(), Criteria.where("appointment").is(findAllParams.getAppointment()))
                .add(findAllParams.getDoctor(), Criteria.where("doctor").is(findAllParams.getDoctor()))
                .add(findAllParams.getDiagnosis(),
                        Criteria.where("diagnosis").regex(findAllParams.getDiagnosis() == null ? ".*" : findAllParams.getDiagnosis(), "i"))
                .add(findAllParams.getDateOfVisitStart(), Criteria.where("dateOfVisit").gte(findAllParams.getDateOfVisitStart()))
                .add(findAllParams.getDateOfVisitEnd(), Criteria.where("dateOfVisit").lte(findAllParams.getDateOfVisitEnd()))
                .with(pageable)
                .build();

        Page<MedicalRecord> medicalRecordPage = PageableExecutionUtils.getPage(mongoTemplate.find(query, MedicalRecord.class),
                pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1),
                        MedicalRecord.class));

        log.info(LogList.FIND_ALL_MEDICAL_RECORDS);
        return PageResponse.<MedicalRecordResponse>builder()
                .objectList(medicalRecordMapper.toResponses(medicalRecordPage.getContent()))
                .totalElements(medicalRecordPage.getTotalElements())
                .totalPages(medicalRecordPage.getTotalPages())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public MedicalRecordResponse findById(String medicalRecordId) {
        MedicalRecord medicalRecord = getOrThrow(medicalRecordId);
        log.info(LogList.FIND_MEDICAL_RECORD, medicalRecordId);
        return medicalRecordMapper.toResponse(medicalRecord);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MedicalRecordResponse> search(String searchLine) {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findBySearchLine(searchLine);
        log.info(LogList.SEARCH_RECORDS, medicalRecords);
        return medicalRecordMapper.toResponses(medicalRecords);
    }

    @Transactional
    @Override
    public void deleteFile(String medicalRecordId, String fileKey) {
        MedicalRecord medicalRecord = getOrThrow(medicalRecordId);
        if (medicalRecord.getFileKeys().remove(fileKey)) {
            medicalRecordRepository.save(medicalRecord);
            fileService.delete(fileKey);
        } else throw new MedicalRecordMissingFileKeyException();
    }

    @Override
    public List<MedicalRecordResponse> findByDoctorId(UUID doctorId) {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findByDoctor(doctorId);
        return medicalRecordMapper.toResponses(medicalRecords);
    }

    @Override
    public List<MedicalRecordResponse> findByPatientId(UUID patientId) {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findByPatient(patientId);
        return medicalRecordMapper.toResponses(medicalRecords);
    }

    @Override
    public MedicalRecordResponse findByAppointmentId(String appointmentId) {
        MedicalRecord medicalRecord = medicalRecordRepository.findByAppointment(appointmentId);
        return medicalRecordMapper.toResponse(medicalRecord);
    }

    public MedicalRecord getOrThrow(String medicalRecordId) {
        Optional<MedicalRecord> optionalMedicalRecord = medicalRecordRepository.findById(medicalRecordId);
        return optionalMedicalRecord.orElseThrow(MedicalRecordNotFoundException::new);
    }

    private AppointmentResponse getAppointment(String appointmentId) {
        try {
            return appointmentFeignClient.findAppointment(appointmentId);
        } catch (OtherServiceNotFoundException e) {
            throw new AppointmentNotFoundException();
        }
    }

}
