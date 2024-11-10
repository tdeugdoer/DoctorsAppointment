package com.tserashkevich.appointmentservice.validators.validAnnotations;

import com.tserashkevich.appointmentservice.validators.DoctorExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {DoctorExistValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DoctorExist {
    String message() default "Doctor not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}