package com.tserashkevich.appointmentservice.services;

import com.tserashkevich.appointmentservice.dtos.PageResponse;
import com.tserashkevich.appointmentservice.dtos.appointment.AppointmentFindAllParams;
import com.tserashkevich.appointmentservice.dtos.appointment.AppointmentResponse;
import com.tserashkevich.appointmentservice.models.Appointment;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {
    AppointmentResponse create(List<Appointment> appointments);

    void delete(String appointmentId);

    PageResponse<AppointmentResponse> findAll(AppointmentFindAllParams appointmentFindAllParams);

    AppointmentResponse findById(String appointmentId);

    List<AppointmentResponse> search(String searchLine);

    AppointmentResponse free(String appointmentId);

    AppointmentResponse book(String appointmentId, UUID patientId, UUID serviceId);

    AppointmentResponse checkIn(String appointmentId);

    AppointmentResponse inProgress(String appointmentId);

    AppointmentResponse complete(String appointmentId);

    AppointmentResponse noShow(String appointmentId);

    List<AppointmentResponse> findFreeWithDoctorId(UUID doctorId);

}
