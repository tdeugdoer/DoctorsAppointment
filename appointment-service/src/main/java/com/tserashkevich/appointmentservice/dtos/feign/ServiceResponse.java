package com.tserashkevich.appointmentservice.dtos.feign;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
public class ServiceResponse {
    private final UUID id;
    private final String name;
    private final String specialization;
    private final BigDecimal price;
    private final String description;
}
