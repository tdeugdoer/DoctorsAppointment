package com.tserashkevich.doctorservice.services;

import com.tserashkevich.doctorservice.dtos.DoctorRequest;
import com.tserashkevich.doctorservice.dtos.DoctorResponse;
import com.tserashkevich.doctorservice.dtos.FindAllParams;
import com.tserashkevich.doctorservice.dtos.PageResponse;

import java.util.List;
import java.util.UUID;

public interface DoctorService {
    DoctorResponse create(DoctorRequest doctorRequest);

    DoctorResponse update(UUID id, DoctorRequest doctorRequest);

    void delete(UUID doctorId);

    PageResponse<DoctorResponse> findAll(FindAllParams findAllParams);

    DoctorResponse findById(UUID doctorId);

    List<DoctorResponse> search(String searchLine);
}
