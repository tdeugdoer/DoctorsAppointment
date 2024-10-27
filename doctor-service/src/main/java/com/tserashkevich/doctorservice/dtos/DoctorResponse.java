package com.tserashkevich.doctorservice.dtos;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class DoctorResponse {
    private final UUID id;
    private final String name;
    private final String surname;
    private final String patronymic;
    private final String specialization;
    private final String gender;
    private final String phoneNumber;
    private final Integer experience;
    private final LocalDate birthDate;
}
