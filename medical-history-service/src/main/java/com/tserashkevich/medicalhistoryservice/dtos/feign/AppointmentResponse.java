package com.tserashkevich.medicalhistoryservice.dtos.feign;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class AppointmentResponse {
    private final String id;
    private final UUID patient;
    private final String status;
    private final LocalDateTime date;
    private final BigDecimal price;
    private final String description;
    private final String doctorWorkDayId;
    private final List<ServiceResponse> service;
    private final DoctorResponse doctor;

}