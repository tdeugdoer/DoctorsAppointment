package com.tserashkevich.medicalhistoryservice.mappers;


import com.tserashkevich.medicalhistoryservice.dtos.MedicalRecordRequest;
import com.tserashkevich.medicalhistoryservice.dtos.MedicalRecordResponse;
import com.tserashkevich.medicalhistoryservice.dtos.UpdateMedicalRecordRequest;
import com.tserashkevich.medicalhistoryservice.dtos.feign.AppointmentResponse;
import com.tserashkevich.medicalhistoryservice.models.MedicalRecord;
import com.tserashkevich.medicalhistoryservice.utils.MedicalRecordMapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = MedicalRecordMapperUtil.class
)
public interface MedicalRecordMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fileKeys", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "dateOfVisit", ignore = true)
    MedicalRecord toModel(MedicalRecordRequest medicalRecordRequest);

    @Mapping(target = "files", qualifiedByName = "getFileInformation", source = "fileKeys")
    MedicalRecordResponse toResponse(MedicalRecord medicalRecord);

    List<MedicalRecordResponse> toResponses(List<MedicalRecord> medicalHistories);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "dateOfVisit", ignore = true)
    @Mapping(target = "appointment", ignore = true)
    @Mapping(target = "fileKeys", ignore = true)
    void updateModel(@MappingTarget MedicalRecord medicalRecord, UpdateMedicalRecordRequest updateMedicalRecordRequest);

    @Mapping(target = "dateOfVisit", source = "date")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fileKeys", ignore = true)
    @Mapping(target = "treatments", ignore = true)
    @Mapping(target = "recommendations", ignore = true)
    @Mapping(target = "diagnosis", ignore = true)
    @Mapping(target = "appointment", ignore = true)
    @Mapping(target = "allergies", ignore = true)
    @Mapping(target = "notes", ignore = true)
    void updateModel(@MappingTarget MedicalRecord medicalRecord, AppointmentResponse appointmentResponse);
}
