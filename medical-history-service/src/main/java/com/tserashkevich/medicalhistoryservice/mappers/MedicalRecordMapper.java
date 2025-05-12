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

    default void updateModel(@MappingTarget MedicalRecord medicalRecord, AppointmentResponse appointmentResponse) {
        medicalRecord.setPatient(appointmentResponse.getPatient());
        medicalRecord.setDoctor(appointmentResponse.getDoctor().getId());
    }

}
