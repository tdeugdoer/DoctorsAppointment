package com.tserashkevich.patientservice.services.impl;

import com.querydsl.core.types.Predicate;
import com.tserashkevich.patientservice.dtos.FindAllParams;
import com.tserashkevich.patientservice.dtos.PageResponse;
import com.tserashkevich.patientservice.dtos.PatientRequest;
import com.tserashkevich.patientservice.dtos.PatientResponse;
import com.tserashkevich.patientservice.exceptions.PatientNotFoundException;
import com.tserashkevich.patientservice.exceptions.PhoneAlreadyExistException;
import com.tserashkevich.patientservice.mappers.PatientMapper;
import com.tserashkevich.patientservice.models.Patient;
import com.tserashkevich.patientservice.models.QPatient;
import com.tserashkevich.patientservice.repositories.PatientRepository;
import com.tserashkevich.patientservice.services.ImageService;
import com.tserashkevich.patientservice.services.PatientService;
import com.tserashkevich.patientservice.utils.LogList;
import com.tserashkevich.patientservice.utils.QPredicates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final ImageService imageService;

    @Override
    public PatientResponse create(PatientRequest patientRequest, MultipartFile file) {
        checkPhoneExists(patientRequest.getPhoneNumber());
        Patient patient = patientMapper.toModel(patientRequest);

        patient.setImage(imageService.upload(file));
        patientRepository.save(patient);

        log.info(LogList.CREATE_PATIENT, patient.getId());
        return patientMapper.toResponse(patient);

    }

    @Override
    public PatientResponse update(UUID patientId, PatientRequest patientRequest, MultipartFile file) {
        checkPhoneExists(patientRequest.getPhoneNumber(), patientId);
        Patient patient = getOrThrow(patientId);

        imageService.update(patient.getImage(), file);
        patientMapper.updateModel(patient, patientRequest);

        log.info(LogList.EDIT_PATIENT, patientId);
        return patientMapper.toResponse(patient);
    }

    @Override
    public void delete(UUID patientId) {
        Patient patient = getOrThrow(patientId);

        patientRepository.delete(patient);
        imageService.delete(patient.getImage());

        log.info(LogList.DELETE_PATIENT, patientId);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<PatientResponse> findAll(FindAllParams findAllParams) {
        Pageable pageable = PageRequest.of(findAllParams.getPage(), findAllParams.getLimit(), findAllParams.getSort());
        Predicate predicate = QPredicates.builder()
                .add(findAllParams.getGender(), QPatient.patient.gender::eq)
                .add(findAllParams.getBirthDateEnd(), QPatient.patient.birthDate::before)
                .add(findAllParams.getBirthDateStart(), QPatient.patient.birthDate::after)
                .build();

        Page<Patient> patientPage = patientRepository.findAll(predicate, pageable);
        List<PatientResponse> patientResponses = patientMapper.toResponses(patientPage.getContent());

        log.info(LogList.FIND_ALL_PATIENTS);
        return PageResponse.<PatientResponse>builder()
                .objectList(patientResponses)
                .totalElements(patientPage.getTotalElements())
                .totalPages(patientPage.getTotalPages())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public PatientResponse findById(UUID patientId) {
        Patient patient = getOrThrow(patientId);
        log.info(LogList.FIND_PATIENT, patientId);
        return patientMapper.toResponse(patient);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PatientResponse> search(String searchLine) {
        log.info(LogList.SEARCH_PATIENT, searchLine);
        return patientMapper.toResponses(patientRepository.search(searchLine));
    }

    @Override
    public Boolean exist(UUID patientId) {
        return patientRepository.existsById(patientId);
    }

    public Patient getOrThrow(UUID patientId) {
        Optional<Patient> optionalPassenger = patientRepository.findById(patientId);
        return optionalPassenger.orElseThrow(PatientNotFoundException::new);
    }

    public void checkPhoneExists(String phoneNumber) {
        Optional<Patient> patient = patientRepository.findByPhoneNumber(phoneNumber);
        if (patient.isPresent())
            throw new PhoneAlreadyExistException();
    }

    public void checkPhoneExists(String phoneNumber, UUID patientId) {
        Optional<Patient> patient = patientRepository.findByPhoneNumber(phoneNumber);
        if (patient.isPresent() && !patient.get().getId().equals(patientId))
            throw new PhoneAlreadyExistException();
    }
}
