package com.tserashkevich.medicalhistoryservice.services;

import com.tserashkevich.medicalhistoryservice.dtos.FindAllParams;
import com.tserashkevich.medicalhistoryservice.dtos.MedicalRecordRequest;
import com.tserashkevich.medicalhistoryservice.dtos.MedicalRecordResponse;
import com.tserashkevich.medicalhistoryservice.dtos.PageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MedicalRecordService {
    MedicalRecordResponse create(MedicalRecordRequest medicalRecordRequest, List<MultipartFile> files);

    MedicalRecordResponse update(String id, MedicalRecordRequest medicalRecordRequest, List<MultipartFile> files);

    void delete(String medicalHistoryId);

    PageResponse<MedicalRecordResponse> findAll(FindAllParams findAllParams);

    MedicalRecordResponse findById(String medicalHistoryId);

    List<MedicalRecordResponse> search(String searchLine);
}
