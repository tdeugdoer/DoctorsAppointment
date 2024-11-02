package com.tserashkevich.patientservice.mappers;

import com.tserashkevich.patientservice.dtos.PatientRequest;
import com.tserashkevich.patientservice.dtos.PatientResponse;
import com.tserashkevich.patientservice.models.Patient;
import com.tserashkevich.patientservice.utils.PatientMapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = PatientMapperUtil.class)
public interface PatientMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", ignore = true)
    Patient toModel(PatientRequest patientRequest);

    @Mapping(target = "image", qualifiedByName = "getImageUrl", source = "image")
    PatientResponse toResponse(Patient patient);

    List<PatientResponse> toResponses(List<Patient> patients);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", ignore = true)
    void updateModel(@MappingTarget Patient patient, PatientRequest patientRequest);
}
