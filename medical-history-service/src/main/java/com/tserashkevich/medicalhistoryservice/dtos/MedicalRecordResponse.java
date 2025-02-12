package com.tserashkevich.medicalhistoryservice.dtos;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class MedicalRecordResponse {
    private final String id;
    private final UUID appointment;
    private final UUID patient;
    private final UUID doctor;
    private final LocalDate dateOfVisit;
    private final String diagnosis;
    private final List<String> treatments;
    private final List<String> allergies;
    private final List<String> recommendations;
    private final List<FileInformation> files;
    private final String notes;

    @Getter
    @Builder
    public static class FileInformation {
        private final String fileKey;
        private final String link;
    }
}
