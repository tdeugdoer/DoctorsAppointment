package com.tserashkevich.appointmentservice.validators;

import com.tserashkevich.appointmentservice.validators.validAnnotations.PatientExist;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class PatientExistValidator implements ConstraintValidator<PatientExist, String> {
    private final PatientFeignClient patientFeignClient;

    @Override
    public boolean isValid(String patientId, ConstraintValidatorContext context) {
        return patientFeignClient.getExistPatient(UUID.fromString(patientId));
    }
}
