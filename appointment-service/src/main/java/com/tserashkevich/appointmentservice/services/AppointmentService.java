package com.tserashkevich.appointmentservice.services;

import com.tserashkevich.appointmentservice.dtos.AppointmentResponse;
import com.tserashkevich.appointmentservice.dtos.CreateAppointmentRequest;
import com.tserashkevich.appointmentservice.dtos.FindAllParams;
import com.tserashkevich.appointmentservice.dtos.PageResponse;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {
    AppointmentResponse create(CreateAppointmentRequest createAppointmentRequest);

    void delete(UUID appointmentId);

    PageResponse<AppointmentResponse> findAll(FindAllParams findAllParams);

    AppointmentResponse findById(UUID appointmentId);

    List<AppointmentResponse> search(String searchLine);

    AppointmentResponse book(UUID appointmentId, UUID patientId);

    AppointmentResponse complete(UUID appointmentId);
}
