package com.tserashkevich.medicalhistoryservice.services;

import com.tserashkevich.medicalhistoryservice.dtos.FindAllParams;
import com.tserashkevich.medicalhistoryservice.dtos.MedicalHistoryRequest;
import com.tserashkevich.medicalhistoryservice.dtos.MedicalHistoryResponse;
import com.tserashkevich.medicalhistoryservice.dtos.PageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface MedicalHistoryService {
    MedicalHistoryResponse create(MedicalHistoryRequest medicalHistoryRequest, MultipartFile file);

    MedicalHistoryResponse update(UUID id, MedicalHistoryRequest medicalHistoryRequest, MultipartFile file);

    void delete(UUID medicalHistoryId);

    PageResponse<MedicalHistoryResponse> findAll(FindAllParams findAllParams);

    MedicalHistoryResponse findById(UUID medicalHistoryId);

    List<MedicalHistoryResponse> search(String searchLine);
}
