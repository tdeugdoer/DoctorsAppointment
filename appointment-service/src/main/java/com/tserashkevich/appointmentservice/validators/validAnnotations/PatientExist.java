package com.tserashkevich.appointmentservice.validators.validAnnotations;

import com.tserashkevich.appointmentservice.validators.PatientExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {PatientExistValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PatientExist {
    String message() default "Patient not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}