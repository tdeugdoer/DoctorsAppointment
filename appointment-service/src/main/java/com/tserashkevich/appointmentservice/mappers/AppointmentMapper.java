package com.tserashkevich.appointmentservice.mappers;

import com.tserashkevich.appointmentservice.dtos.AppointmentResponse;
import com.tserashkevich.appointmentservice.dtos.CreateAppointmentRequest;
import com.tserashkevich.appointmentservice.models.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppointmentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "price", ignore = true)
    Appointment toModel(CreateAppointmentRequest createAppointmentRequest);

    AppointmentResponse toResponse(Appointment appointment);

    List<AppointmentResponse> toResponses(List<Appointment> appointments);
}
