package com.tserashkevich.appointmentservice.dtos.doctorWorkDay;

import com.tserashkevich.appointmentservice.utils.PatternList;
import com.tserashkevich.appointmentservice.utils.ValidationList;
import com.tserashkevich.appointmentservice.validators.validAnnotations.TimeOrder;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class DoctorWorkDayRequest {
    @NotBlank(message = ValidationList.DOCTOR_ID_REQUIRED)
    @Pattern(regexp = PatternList.UUID_PATTERN, message = ValidationList.WRONG_UUID_FORMAT)
    private final String doctor;

    private final List<
            @NotBlank(message = ValidationList.SERVICE_ID_REQUIRED)
            @Pattern(regexp = PatternList.UUID_PATTERN, message = ValidationList.WRONG_UUID_FORMAT)
                    String> services;

    @NotNull(message = ValidationList.DATE_REQUIRED)
    @FutureOrPresent(message = ValidationList.WRONG_DATE)
    private final LocalDate date;

    @NotNull
    @TimeOrder
    private final TimeIntervalDto workTime;

}