package com.tserashkevich.patientservice.dtos;

import com.tserashkevich.patientservice.utils.PatternList;
import com.tserashkevich.patientservice.utils.ValidationList;
import com.tserashkevich.patientservice.validators.validAnnotations.ValidPatientPhoneNumber;
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

    @NotBlank(message = ValidationList.PATRONYMIC_REQUIRED)
    @Size(max = 15, message = ValidationList.WRONG_MAX_PATRONYMIC_LENGTH)
    private final String patronymic;

    @NotBlank(message = ValidationList.GENDER_REQUIRED)
    @Pattern(regexp = PatternList.GENDER_PATTERN, message = ValidationList.WRONG_GENDER)
    private final String gender;

    @ValidPatientPhoneNumber(message = ValidationList.PHONE_NUMBER_ALREADY_EXIST)
    @NotBlank(message = ValidationList.PHONE_REQUIRED)
    @Pattern(regexp = PatternList.PHONE_PATTERN, message = ValidationList.WRONG_PHONE_FORMAT)
    private final String phoneNumber;

    @NotNull(message = ValidationList.BIRTH_DATE_REQUIRED)
    @Past(message = ValidationList.WRONG_BIRTH_DATE)
    private final LocalDate birthDate;
}
