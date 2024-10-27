package com.tserashkevich.patientservice.services;

import com.tserashkevich.patientservice.dtos.FindAllParams;
import com.tserashkevich.patientservice.dtos.PageResponse;
import com.tserashkevich.patientservice.dtos.PatientRequest;
import com.tserashkevich.patientservice.dtos.PatientResponse;

import java.util.UUID;

public interface PatientService {
    PatientResponse create(PatientRequest PatientRequest);

    PatientResponse update(UUID id, PatientRequest PatientRequest);

    void delete(UUID PatientId);

    PageResponse<PatientResponse> findAll(FindAllParams findAllParams);

    PatientResponse findById(UUID PatientId);

    Boolean existById(UUID PatientId);

    Boolean existByPhoneNumber(String phoneNumber);
}
