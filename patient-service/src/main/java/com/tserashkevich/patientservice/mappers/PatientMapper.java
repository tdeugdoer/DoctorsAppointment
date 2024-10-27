package com.tserashkevich.patientservice.mappers;

import com.tserashkevich.patientservice.dtos.PatientRequest;
import com.tserashkevich.patientservice.dtos.PatientResponse;
import com.tserashkevich.patientservice.models.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientMapper {
    @Mapping(target = "id", ignore = true)
    Patient toModel(PatientRequest patientRequest);

    PatientResponse toResponse(Patient Patient);

    List<PatientResponse> toResponses(List<Patient> patients);

    @Mapping(target = "id", ignore = true)
    void updateModel(@MappingTarget Patient patient, PatientRequest patientRequest);
}
