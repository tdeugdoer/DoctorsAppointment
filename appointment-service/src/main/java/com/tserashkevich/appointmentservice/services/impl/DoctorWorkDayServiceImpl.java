package com.tserashkevich.appointmentservice.services.impl;

import com.tserashkevich.appointmentservice.dtos.PageResponse;
import com.tserashkevich.appointmentservice.dtos.doctorWorkDay.DoctorWorkDayFindAllParams;
import com.tserashkevich.appointmentservice.dtos.doctorWorkDay.DoctorWorkDayRequest;
import com.tserashkevich.appointmentservice.dtos.doctorWorkDay.DoctorWorkDayResponse;
import com.tserashkevich.appointmentservice.exceptions.DoctorNotMatchServiceException;
import com.tserashkevich.appointmentservice.exceptions.DoctorWorkDayNotFoundException;
import com.tserashkevich.appointmentservice.feign.ExternalServiceClient;
import com.tserashkevich.appointmentservice.mappers.DoctorWorkDayMapper;
import com.tserashkevich.appointmentservice.models.DoctorWorkDay;
import com.tserashkevich.appointmentservice.repositories.DoctorWorkDayRepository;
import com.tserashkevich.appointmentservice.services.AppointmentGenerateService;
import com.tserashkevich.appointmentservice.services.DoctorWorkDayService;
import com.tserashkevich.appointmentservice.utils.LogList;
import com.tserashkevich.appointmentservice.utils.QueryPredicate;
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

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class DoctorWorkDayServiceImpl implements DoctorWorkDayService {
    private final DoctorWorkDayRepository doctorWorkDayRepository;
    private final DoctorWorkDayMapper doctorWorkDayMapper;
    private final MongoTemplate mongoTemplate;
    private final AppointmentGenerateService appointmentGenerateService;
    private final ExternalServiceClient externalServiceClient;

    @Override
    public DoctorWorkDayResponse create(DoctorWorkDayRequest doctorWorkDayRequest) {
        DoctorWorkDay doctorWorkDay = doctorWorkDayMapper.toModel(doctorWorkDayRequest);
        doctorWorkDay.setDoctor(externalServiceClient.getDoctor(UUID.fromString(doctorWorkDayRequest.getDoctor())));
        doctorWorkDay.setServices(externalServiceClient.getServices(doctorWorkDayRequest.getServices()));
        doctorWorkDay.getServices().forEach(service ->
                checkMatchingSpecialization(service.getSpecialization(), doctorWorkDay.getDoctor().getSpecialization()));

        doctorWorkDayRepository.save(doctorWorkDay);
        appointmentGenerateService.generateAppointments(doctorWorkDay);

        log.info(LogList.CREATE_DOCTOR_WORK_DAY, doctorWorkDay.getId());
        return doctorWorkDayMapper.toResponse(doctorWorkDay);
    }

    @Override
    public void delete(String doctorWorkDayId) {
        DoctorWorkDay doctorWorkDay = getOrThrow(doctorWorkDayId);

        doctorWorkDayRepository.delete(doctorWorkDay);
        appointmentGenerateService.deleteAppointments(doctorWorkDay.getId());

        log.info(LogList.DELETE_DOCTOR_WORK_DAY, doctorWorkDay.getId());
    }

    @Override
    public PageResponse<DoctorWorkDayResponse> findAll(DoctorWorkDayFindAllParams doctorWorkDayFindAllParams) {
        Pageable pageable = PageRequest.of(doctorWorkDayFindAllParams.getPage(), doctorWorkDayFindAllParams.getLimit(), doctorWorkDayFindAllParams.getSort());
        Query query = QueryPredicate.builder()
                .add(doctorWorkDayFindAllParams.getDoctor(),
                        Criteria.where("doctor.id").is(doctorWorkDayFindAllParams.getDoctor()))
                .add(doctorWorkDayFindAllParams.getServices(),
                        Criteria.where("services.id").in(doctorWorkDayFindAllParams.getServices()))
                .add(doctorWorkDayFindAllParams.getDateStart(),
                        Criteria.where("date").gte(doctorWorkDayFindAllParams.getDateStart()))
                .add(doctorWorkDayFindAllParams.getDateEnd(),
                        Criteria.where("date").lte(doctorWorkDayFindAllParams.getDateEnd()))
                .with(pageable)
                .build();

        Page<DoctorWorkDay> doctorWorkDayPage = PageableExecutionUtils.getPage(mongoTemplate.find(query, DoctorWorkDay.class),
                pageable,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1),
                        DoctorWorkDay.class));

        log.info(LogList.FIND_ALL_DOCTOR_WORK_DAYS);
        return PageResponse.<DoctorWorkDayResponse>builder()
                .objectList(doctorWorkDayMapper.toResponses(doctorWorkDayPage.getContent()))
                .totalElements(doctorWorkDayPage.getTotalElements())
                .totalPages(doctorWorkDayPage.getTotalPages())
                .build();
    }

    @Override
    public DoctorWorkDayResponse findById(String doctorWorkDayId) {
        DoctorWorkDay doctorWorkDay = getOrThrow(doctorWorkDayId);
        log.info(LogList.FIND_DOCTOR_WORK_DAY, doctorWorkDayId);
        return doctorWorkDayMapper.toResponse(doctorWorkDay);
    }

    private DoctorWorkDay getOrThrow(String doctorWorkDayId) {
        Optional<DoctorWorkDay> optionalDoctorWorkDay = doctorWorkDayRepository.findById(doctorWorkDayId);
        return optionalDoctorWorkDay.orElseThrow(DoctorWorkDayNotFoundException::new);
    }

    private void checkMatchingSpecialization(String serviceSpecialization, String doctorSpecialization) {
        if (!serviceSpecialization.equals(doctorSpecialization)) {
            throw new DoctorNotMatchServiceException();
        }
    }

}
