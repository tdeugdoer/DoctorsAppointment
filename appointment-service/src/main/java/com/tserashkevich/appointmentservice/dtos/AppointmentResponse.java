package com.tserashkevich.appointmentservice.dtos;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class AppointmentResponse {
    private UUID id;
    private UUID doctor;
    private UUID patient;
    private String status;
    private LocalDateTime date;
    private BigDecimal price;
}
