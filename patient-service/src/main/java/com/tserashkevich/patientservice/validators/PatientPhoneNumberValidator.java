package com.tserashkevich.patientservice.validators;

import com.tserashkevich.patientservice.services.PatientService;
import com.tserashkevich.patientservice.validators.validAnnotations.ValidPatientPhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PatientPhoneNumberValidator implements ConstraintValidator<ValidPatientPhoneNumber, String> {
    private final PatientService passengerService;

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        return !passengerService.existByPhoneNumber(phoneNumber);
    }
}
