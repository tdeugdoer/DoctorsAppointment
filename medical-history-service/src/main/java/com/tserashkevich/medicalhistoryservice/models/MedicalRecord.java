package com.tserashkevich.medicalhistoryservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "medical_history")
public class MedicalRecord {
    @Id
    private String id;
    private UUID patient;
    private UUID appointment;
    private UUID doctor;
    private LocalDate dateOfVisit;
    private String diagnosis;
    private List<String> treatments;
    private List<String> allergies;
    private List<String> recommendations;
    private List<String> fileKeys;
    private String notes;

    public void addFileKeys(List<String> fileKeys) {
        if (this.fileKeys == null) {
            this.fileKeys = fileKeys;
        } else this.fileKeys.addAll(fileKeys);
    }
}
