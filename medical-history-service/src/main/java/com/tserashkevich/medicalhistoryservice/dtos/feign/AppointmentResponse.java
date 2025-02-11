package com.tserashkevich.medicalhistoryservice.dtos.feign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponse {
    private UUID id;
    private UUID service;
    private UUID doctor;
    private UUID patient;
    private String status;
    private LocalDateTime date;
    private BigDecimal price;
}
