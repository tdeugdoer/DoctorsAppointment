package com.tserashkevich.medicalhistoryservice.services;

import com.tserashkevich.medicalhistoryservice.dtos.FindAllParams;
import com.tserashkevich.medicalhistoryservice.dtos.MedicalRecordRequest;
import com.tserashkevich.medicalhistoryservice.dtos.MedicalRecordResponse;
import com.tserashkevich.medicalhistoryservice.dtos.PageResponse;
import com.tserashkevich.medicalhistoryservice.dtos.UpdateMedicalRecordRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface MedicalRecordService {
    MedicalRecordResponse create(MedicalRecordRequest medicalRecordRequest, List<MultipartFile> files);

    MedicalRecordResponse update(String medicalRecordId, UpdateMedicalRecordRequest updateMedicalRecordRequest, List<MultipartFile> files);

    void delete(String medicalHistoryId);

    PageResponse<MedicalRecordResponse> findAll(FindAllParams findAllParams);

    MedicalRecordResponse findById(String medicalHistoryId);

    List<MedicalRecordResponse> search(String searchLine);

    void deleteFile(String medicalRecordId, String fileKey);

    List<MedicalRecordResponse> findByDoctorId(UUID doctorId);

    List<MedicalRecordResponse> findByPatientId(UUID patientId);

    MedicalRecordResponse findByAppointmentId(String appointmentId);

}
