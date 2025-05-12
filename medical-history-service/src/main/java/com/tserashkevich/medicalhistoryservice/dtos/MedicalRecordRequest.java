package com.tserashkevich.medicalhistoryservice.dtos;

import com.tserashkevich.medicalhistoryservice.utils.ValidationList;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MedicalRecordRequest {
    @NotBlank(message = ValidationList.APPOINTMENT_ID_REQUIRED)
    private final String appointment;

    @NotBlank(message = ValidationList.DIAGNOSIS_REQUIRED)
    private final String diagnosis;

    private final List<String> treatments;
    private final List<String> allergies;
    private final List<String> recommendations;
    private final String notes;

}