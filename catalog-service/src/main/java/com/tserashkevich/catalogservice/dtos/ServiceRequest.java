package com.tserashkevich.catalogservice.dtos;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ServiceRequest {
    private final String name;
    private final String specialization;
    private final BigDecimal price;
    private final String description;
}
