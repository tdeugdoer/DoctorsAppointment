package com.tserashkevich.doctorservice.services.impl;

import com.querydsl.core.types.Predicate;
import com.tserashkevich.doctorservice.dtos.DoctorRequest;
import com.tserashkevich.doctorservice.dtos.DoctorResponse;
import com.tserashkevich.doctorservice.dtos.FindAllParams;
import com.tserashkevich.doctorservice.dtos.PageResponse;
import com.tserashkevich.doctorservice.exceptions.DoctorNotFoundException;
import com.tserashkevich.doctorservice.exceptions.PhoneAlreadyExistException;
import com.tserashkevich.doctorservice.mappers.DoctorMapper;
import com.tserashkevich.doctorservice.models.Doctor;
import com.tserashkevich.doctorservice.models.QDoctor;
import com.tserashkevich.doctorservice.repositories.DoctorRepository;
import com.tserashkevich.doctorservice.services.DoctorService;
import com.tserashkevich.doctorservice.services.ImageService;
import com.tserashkevich.doctorservice.utils.LogList;
import com.tserashkevich.doctorservice.utils.QPredicates;
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
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final ImageService imageService;

    @Override
    public DoctorResponse create(DoctorRequest doctorRequest, MultipartFile file) {
        checkPhoneExists(doctorRequest.getPhoneNumber());
        Doctor doctor = doctorMapper.toModel(doctorRequest);

        doctor.setImage(imageService.upload(file));
        doctorRepository.save(doctor);

        log.info(LogList.CREATE_DOCTOR, doctor.getId());
        return doctorMapper.toResponse(doctor);
    }

    @Override
    public DoctorResponse update(UUID doctorId, DoctorRequest doctorRequest, MultipartFile file) {
        checkPhoneExists(doctorRequest.getPhoneNumber(), doctorId);
        Doctor doctor = getOrThrow(doctorId);

        imageService.update(doctor.getImage(), file);
        doctorMapper.updateModel(doctor, doctorRequest);

        log.info(LogList.EDIT_DOCTOR, doctorId);
        return doctorMapper.toResponse(doctor);
    }

    @Override
    public void delete(UUID doctorId) {
        Doctor doctor = getOrThrow(doctorId);

        doctorRepository.delete(doctor);
        imageService.delete(doctor.getImage());

        log.info(LogList.DELETE_DOCTOR, doctorId);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<DoctorResponse> findAll(FindAllParams findAllParams) {
        Pageable pageable = PageRequest.of(findAllParams.getPage(), findAllParams.getLimit(), findAllParams.getSort());
        Predicate predicate = QPredicates.builder()
                .add(findAllParams.getSpecialization(), QDoctor.doctor.specialization::eq)
                .add(findAllParams.getGender(), QDoctor.doctor.gender::eq)
                .add(findAllParams.getExperienceStart(), QDoctor.doctor.experience::goe)
                .add(findAllParams.getExperienceEnd(), QDoctor.doctor.experience::lt)
                .add(findAllParams.getBirthDateStart(), QDoctor.doctor.birthDate::after)
                .add(findAllParams.getBirthDateEnd(), QDoctor.doctor.birthDate::before)
                .add(findAllParams.getBirthDateStart(), QDoctor.doctor.birthDate::after)
                .build();

        Page<Doctor> doctorPage = doctorRepository.findAll(predicate, pageable);
        List<DoctorResponse> doctorResponses = doctorMapper.toResponses(doctorPage.getContent());

        log.info(LogList.FIND_ALL_DOCTORS);
        return PageResponse.<DoctorResponse>builder()
                .objectList(doctorResponses)
                .totalElements(doctorPage.getTotalElements())
                .totalPages(doctorPage.getTotalPages())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public DoctorResponse findById(UUID doctorId) {
        Doctor doctor = getOrThrow(doctorId);
        log.info(LogList.FIND_DOCTOR, doctorId);
        return doctorMapper.toResponse(doctor);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DoctorResponse> search(String searchLine) {
        log.info(LogList.SEARCH_DOCTOR, searchLine);
        return doctorMapper.toResponses(doctorRepository.search(searchLine));
    }

    @Override
    public Boolean exist(UUID doctorId) {
        return doctorRepository.existsById(doctorId);
    }

    public Doctor getOrThrow(UUID doctorId) {
        Optional<Doctor> optionalPassenger = doctorRepository.findById(doctorId);
        return optionalPassenger.orElseThrow(DoctorNotFoundException::new);
    }

    public void checkPhoneExists(String phoneNumber) {
        Optional<Doctor> doctor = doctorRepository.findByPhoneNumber(phoneNumber);
        if (doctor.isPresent())
            throw new PhoneAlreadyExistException();
    }

    public void checkPhoneExists(String phoneNumber, UUID doctorId) {
        Optional<Doctor> doctor = doctorRepository.findByPhoneNumber(phoneNumber);
        if (doctor.isPresent() && !doctor.get().getId().equals(doctorId))
            throw new PhoneAlreadyExistException();
    }
}
