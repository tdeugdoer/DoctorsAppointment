package com.tserashkevich.appointmentservice.validators.validAnnotations;

import com.tserashkevich.appointmentservice.validators.TimeOrderValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeOrderValidator.class)
public @interface TimeOrder {
    String message() default "Начало должно быть раньше конца";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}