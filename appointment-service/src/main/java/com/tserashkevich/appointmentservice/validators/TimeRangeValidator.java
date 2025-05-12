package com.tserashkevich.appointmentservice.validators;

import com.tserashkevich.appointmentservice.validators.validAnnotations.TimeRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeRangeValidator implements ConstraintValidator<TimeRange, LocalTime> {
    private LocalTime startTime;
    private LocalTime endTime;

    @Override
    public void initialize(TimeRange constraintAnnotation) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            this.startTime = LocalTime.parse(constraintAnnotation.startTime(), formatter);
            this.endTime = LocalTime.parse(constraintAnnotation.endTime(), formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неверный формат времени в аннотации TimeRange: " + e.getMessage());
        }
    }

    @Override
    public boolean isValid(LocalTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value.isAfter(startTime) && value.isBefore(endTime);
    }

}
