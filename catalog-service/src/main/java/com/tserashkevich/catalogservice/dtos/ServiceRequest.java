package com.tserashkevich.catalogservice.dtos;

import com.tserashkevich.catalogservice.utils.PatternList;
import com.tserashkevich.catalogservice.utils.ValidationList;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ServiceRequest {
    @NotBlank(message = ValidationList.NAME_REQUIRED)
    @Size(max = 100, message = ValidationList.WRONG_MAX_NAME_LENGTH)
    private final String name;

    @NotBlank(message = ValidationList.SPECIALIZATION_REQUIRED)
    @Pattern(regexp = PatternList.SPECIALIZATION_PATTERN, message = ValidationList.WRONG_SPECIALIZATION)
    private final String specialization;

    @NotNull(message = ValidationList.PRICE_REQUIRED)
    @DecimalMin(value = "0", message = ValidationList.LESS_ZERO)
    private final BigDecimal price;

    private final String description;
}
