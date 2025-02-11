package com.tserashkevich.medicalhistoryservice.dtos;

import com.tserashkevich.medicalhistoryservice.utils.PatternList;
import com.tserashkevich.medicalhistoryservice.utils.ValidationList;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MedicalRecordRequest {
    @NotBlank(message = ValidationList.APPOINTMENT_ID_REQUIRED)
    @Pattern(regexp = PatternList.UUID_PATTERN, message = ValidationList.WRONG_UUID_FORMAT)
    private final String appointment;

    @NotBlank(message = ValidationList.DIAGNOSIS_REQUIRED)
    private final String diagnosis;

    private final List<String> treatments;
    private final List<String> allergies;
    private final List<String> recommendations;
    private final String notes;
}