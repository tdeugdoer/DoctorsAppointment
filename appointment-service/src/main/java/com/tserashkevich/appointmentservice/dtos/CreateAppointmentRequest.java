package com.tserashkevich.appointmentservice.dtos;

import com.tserashkevich.appointmentservice.utils.PatternList;
import com.tserashkevich.appointmentservice.utils.ValidationList;
import com.tserashkevich.appointmentservice.validators.validAnnotations.DoctorExist;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class CreateAppointmentRequest {
    @DoctorExist(message = ValidationList.DOCTOR_NOT_EXIST)
    @NotBlank(message = ValidationList.DOCTOR_ID_REQUIRED)
    @Pattern(regexp = PatternList.UUID_PATTERN, message = ValidationList.WRONG_UUID_FORMAT)
    private String doctor;

    @NotNull(message = ValidationList.DATE_REQUIRED)
    @Future(message = ValidationList.WRONG_DATE)
    private LocalDateTime date;

    @NotNull(message = ValidationList.PRICE_REQUIRED)
    @DecimalMin(value = "0", message = ValidationList.LESS_ZERO)
    private BigDecimal price;
}