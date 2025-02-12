package com.tserashkevich.medicalhistoryservice.services;

import com.tserashkevich.medicalhistoryservice.dtos.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MedicalRecordService {
    MedicalRecordResponse create(MedicalRecordRequest medicalRecordRequest, List<MultipartFile> files);

    MedicalRecordResponse update(String medicalRecordId, UpdateMedicalRecordRequest updateMedicalRecordRequest, List<MultipartFile> files);

    void delete(String medicalHistoryId);

    PageResponse<MedicalRecordResponse> findAll(FindAllParams findAllParams);

    MedicalRecordResponse findById(String medicalHistoryId);

    List<MedicalRecordResponse> search(String searchLine);

    void deleteFile(String medicalRecordId, String fileKey);
}
