package com.tserashkevich.appointmentservice.dtos.doctorWorkDay;

import com.tserashkevich.appointmentservice.dtos.feign.DoctorResponse;
import com.tserashkevich.appointmentservice.dtos.feign.ServiceResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class DoctorWorkDayResponse {
    private final String id;
    private final LocalDate date;
    private final TimeIntervalDto workTime;
    private final DoctorResponse doctor;
    private final List<ServiceResponse> services;

}
