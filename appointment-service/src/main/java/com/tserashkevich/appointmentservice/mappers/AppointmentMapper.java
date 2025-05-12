package com.tserashkevich.appointmentservice.mappers;

import com.tserashkevich.appointmentservice.dtos.appointment.AppointmentResponse;
import com.tserashkevich.appointmentservice.models.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppointmentMapper {
    AppointmentResponse toResponse(Appointment appointment);

    List<AppointmentResponse> toResponses(List<Appointment> appointments);

}
