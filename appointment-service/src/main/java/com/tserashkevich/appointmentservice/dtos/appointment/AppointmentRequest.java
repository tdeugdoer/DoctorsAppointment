package com.tserashkevich.appointmentservice.dtos.appointment;

import com.tserashkevich.appointmentservice.utils.PatternList;
import com.tserashkevich.appointmentservice.utils.ValidationList;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class AppointmentRequest {
    private final List<
            @NotBlank(message = ValidationList.SERVICE_ID_REQUIRED)
            @Pattern(regexp = PatternList.UUID_PATTERN, message = ValidationList.WRONG_UUID_FORMAT)
                    String> service;

    @NotBlank(message = ValidationList.DOCTOR_ID_REQUIRED)
    @Pattern(regexp = PatternList.UUID_PATTERN, message = ValidationList.WRONG_UUID_FORMAT)
    private final String doctor;

    @NotNull(message = ValidationList.DATE_REQUIRED)
    @Future(message = ValidationList.WRONG_DATE)
    private final LocalDateTime date;

    @NotBlank(message = ValidationList.DOCTOR_WORK_DAY_ID_REQUIRED)
    private final String doctorWorkDayId;

}