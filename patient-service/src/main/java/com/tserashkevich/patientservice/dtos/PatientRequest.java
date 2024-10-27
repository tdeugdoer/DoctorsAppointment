package com.tserashkevich.patientservice.dtos;

import com.tserashkevich.patientservice.utils.PatternList;
import com.tserashkevich.patientservice.utils.ValidationList;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class PatientRequest {
    @NotBlank(message = ValidationList.NAME_REQUIRED)
    @Size(max = 15, message = ValidationList.WRONG_MAX_NAME_LENGTH)
    private final String name;

    @NotBlank(message = ValidationList.SURNAME_REQUIRED)
    @Size(max = 15, message = ValidationList.WRONG_MAX_SURNAME_LENGTH)
    private final String surname;

    @Size(max = 15, message = ValidationList.WRONG_MAX_PATRONYMIC_LENGTH)
    private final String patronymic;

    @NotBlank(message = ValidationList.GENDER_REQUIRED)
    @Pattern(regexp = PatternList.GENDER_PATTERN, message = ValidationList.WRONG_GENDER)
    private final String gender;

    @NotBlank(message = ValidationList.PHONE_REQUIRED)
    @Pattern(regexp = PatternList.PHONE_PATTERN, message = ValidationList.WRONG_PHONE_FORMAT)
    private final String phoneNumber;

    @NotNull(message = ValidationList.BIRTHDATE_REQUIRED)
    @Past(message = ValidationList.WRONG_BIRTHDATE)
    private final LocalDate birthDate;
}
