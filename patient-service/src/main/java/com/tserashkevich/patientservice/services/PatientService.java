package com.tserashkevich.patientservice.services;

import com.tserashkevich.patientservice.dtos.FindAllParams;
import com.tserashkevich.patientservice.dtos.PageResponse;
import com.tserashkevich.patientservice.dtos.PatientRequest;
import com.tserashkevich.patientservice.dtos.PatientResponse;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    PatientResponse create(PatientRequest patientRequest);

    PatientResponse update(UUID id, PatientRequest patientRequest);

    void delete(UUID patientId);

    PageResponse<PatientResponse> findAll(FindAllParams findAllParams);

    PatientResponse findById(UUID patientId);

    List<PatientResponse> search(String searchLine);
}
