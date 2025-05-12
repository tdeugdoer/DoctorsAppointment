package com.tserashkevich.appointmentservice.mappers;

import com.tserashkevich.appointmentservice.dtos.doctorWorkDay.DoctorWorkDayRequest;
import com.tserashkevich.appointmentservice.dtos.doctorWorkDay.DoctorWorkDayResponse;
import com.tserashkevich.appointmentservice.models.DoctorWorkDay;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DoctorWorkDayMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "services", ignore = true)
    DoctorWorkDay toModel(DoctorWorkDayRequest appointmentRequest);

    DoctorWorkDayResponse toResponse(DoctorWorkDay appointment);

    List<DoctorWorkDayResponse> toResponses(List<DoctorWorkDay> appointments);

}
