package com.tserashkevich.doctorservice.services;

import com.tserashkevich.doctorservice.dtos.DoctorRequest;
import com.tserashkevich.doctorservice.dtos.DoctorResponse;
import com.tserashkevich.doctorservice.dtos.FindAllParams;
import com.tserashkevich.doctorservice.dtos.PageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface DoctorService {
    DoctorResponse create(DoctorRequest doctorRequest, MultipartFile file);

    DoctorResponse update(UUID id, DoctorRequest doctorRequest, MultipartFile file);

    void delete(UUID doctorId);

    PageResponse<DoctorResponse> findAll(FindAllParams findAllParams);

    DoctorResponse findById(UUID doctorId);

    List<DoctorResponse> search(String searchLine);

    Boolean exist(UUID doctorId);
}
