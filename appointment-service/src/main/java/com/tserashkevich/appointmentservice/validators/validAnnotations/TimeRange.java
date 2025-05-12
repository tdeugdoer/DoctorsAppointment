package com.tserashkevich.appointmentservice.validators.validAnnotations;

import com.tserashkevich.appointmentservice.validators.TimeRangeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeRangeValidator.class)
public @interface TimeRange {
    String message() default "Время должно быть между {startTime} и {endTime}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String startTime();

    String endTime();

}