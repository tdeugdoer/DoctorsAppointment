package com.tserashkevich.appointmentservice.validators;

import com.tserashkevich.appointmentservice.dtos.doctorWorkDay.TimeIntervalDto;
import com.tserashkevich.appointmentservice.validators.validAnnotations.TimeOrder;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TimeOrderValidator implements ConstraintValidator<TimeOrder, TimeIntervalDto> {
    @Override
    public boolean isValid(TimeIntervalDto value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value.getStart().isBefore(value.getEnd());
    }

}
