package com.tserashkevich.medicalhistoryservice.dtos;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class MedicalRecordResponse {
    private final UUID id;
    private final UUID appointment;
    private final UUID patient;
    private final LocalDate dateOfVisit;
    private final UUID doctor;
    private final List<String> diagnosis;
    private final List<String> treatments;
    private final List<String> allergies;
    private final List<String> recommendations;
    private final List<String> files;
    private final String notes;
}
