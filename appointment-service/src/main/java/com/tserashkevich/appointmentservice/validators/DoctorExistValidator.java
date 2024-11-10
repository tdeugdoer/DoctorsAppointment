package com.tserashkevich.appointmentservice.validators;

import com.tserashkevich.appointmentservice.feign.DoctorFeignClient;
import com.tserashkevich.appointmentservice.validators.validAnnotations.DoctorExist;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DoctorExistValidator implements ConstraintValidator<DoctorExist, String> {
    private final DoctorFeignClient doctorFeignClient;

    @Override
    public boolean isValid(String doctorId, ConstraintValidatorContext context) {
        return doctorFeignClient.getExistDoctor(UUID.fromString(doctorId));
    }
}
