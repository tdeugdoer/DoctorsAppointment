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
public class MedicalHistory {
    @Id
    private UUID id;
    private UUID patient;
    private LocalDate dateOfVisit;
    private UUID doctor;
    private List<String> diagnosis;
    private List<String> treatments;
    private List<String> allergies;
    private List<String> recommendations;
    private List<String> files;
    private String notes;
}
