package com.tserashkevich.appointmentservice.services;

import com.tserashkevich.appointmentservice.models.DoctorWorkDay;

public interface AppointmentGenerateService {
    void generateAppointments(DoctorWorkDay doctorWorkDay);

    void regenerateAppointments(String doctorWorkDayId);

    void deleteAppointments(String doctorWorkDayId);

    void generateTodayAppointments();

}
