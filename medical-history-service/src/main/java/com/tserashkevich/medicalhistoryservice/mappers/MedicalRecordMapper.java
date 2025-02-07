package com.tserashkevich.medicalhistoryservice.mappers;


import com.tserashkevich.medicalhistoryservice.dtos.MedicalRecordRequest;
import com.tserashkevich.medicalhistoryservice.dtos.MedicalRecordResponse;
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
    MedicalRecord toModel(MedicalRecordRequest medicalRecordRequest);

    @Mapping(target = "files", qualifiedByName = "getFileUrl", source = "fileKeys")
    MedicalRecordResponse toResponse(MedicalRecord medicalRecord);

    List<MedicalRecordResponse> toResponses(List<MedicalRecord> medicalHistories);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fileKeys", ignore = true)
    void updateModel(@MappingTarget MedicalRecord medicalRecord, MedicalRecordRequest medicalRecordRequest);
}
