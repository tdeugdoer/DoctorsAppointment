package com.tserashkevich.doctorservice.mappers;

import com.tserashkevich.doctorservice.dtos.DoctorRequest;
import com.tserashkevich.doctorservice.dtos.DoctorResponse;
import com.tserashkevich.doctorservice.models.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DoctorMapper {
    @Mapping(target = "id", ignore = true)
    Doctor toModel(DoctorRequest doctorRequest);

    DoctorResponse toResponse(Doctor Doctor);

    List<DoctorResponse> toResponses(List<Doctor> doctors);

    @Mapping(target = "id", ignore = true)
    void updateModel(@MappingTarget Doctor doctor, DoctorRequest doctorRequest);
}
