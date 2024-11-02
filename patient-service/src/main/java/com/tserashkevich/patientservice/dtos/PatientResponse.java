package com.tserashkevich.patientservice.dtos;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class PatientResponse {
    private final UUID id;
    private final String name;
    private final String surname;
    private final String patronymic;
    private final String gender;
    private final String phoneNumber;
    private final LocalDate birthDate;
    private final String image;
}
