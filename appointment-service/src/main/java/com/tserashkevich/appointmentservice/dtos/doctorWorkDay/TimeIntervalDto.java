package com.tserashkevich.appointmentservice.dtos.doctorWorkDay;

import com.tserashkevich.appointmentservice.utils.TimeList;
import com.tserashkevich.appointmentservice.utils.ValidationList;
import com.tserashkevich.appointmentservice.validators.validAnnotations.TimeRange;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
public class TimeIntervalDto {
    @NotNull
    @TimeRange(startTime = TimeList.START_WORK_TIME, endTime = TimeList.END_WORK_TIME, message = ValidationList.WRONG_TIME)
    private final LocalTime start;

    @NotNull
    @TimeRange(startTime = TimeList.START_WORK_TIME, endTime = TimeList.END_WORK_TIME, message = ValidationList.WRONG_TIME)
    private final LocalTime end;

}