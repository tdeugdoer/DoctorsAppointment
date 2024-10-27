package com.tserashkevich.patientservice.validators.validAnnotations;

import com.tserashkevich.patientservice.validators.PatientPhoneNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {PatientPhoneNumberValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPatientPhoneNumber {
    String message() default "Patient phone number already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
