package com.tserashkevich.doctorservice.dtos;

import com.tserashkevich.doctorservice.utils.PatternList;
import com.tserashkevich.doctorservice.utils.ValidationList;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class DoctorRequest {
    @NotBlank(message = ValidationList.NAME_REQUIRED)
    @Size(max = 15, message = ValidationList.WRONG_MAX_NAME_LENGTH)
    private final String name;

    @NotBlank(message = ValidationList.SURNAME_REQUIRED)
    @Size(max = 15, message = ValidationList.WRONG_MAX_SURNAME_LENGTH)
    private final String surname;

    @Size(max = 15, message = ValidationList.WRONG_MAX_PATRONYMIC_LENGTH)
    private final String patronymic;

    @NotBlank(message = ValidationList.SPECIALIZATION_REQUIRED)
    @Pattern(regexp = PatternList.SPECIALIZATION_PATTERN, message = ValidationList.WRONG_SPECIALIZATION)
    private final String specialization;

    @NotBlank(message = ValidationList.GENDER_REQUIRED)
    @Pattern(regexp = PatternList.GENDER_PATTERN, message = ValidationList.WRONG_GENDER)
    private final String gender;

    @NotBlank(message = ValidationList.PHONE_REQUIRED)
    @Pattern(regexp = PatternList.PHONE_PATTERN, message = ValidationList.WRONG_PHONE_FORMAT)
    private final String phoneNumber;

    @Min(value = 0, message = ValidationList.EXPERIENCE_LESS_ZERO)
    @Max(value = 100, message = ValidationList.EXPERIENCE_MORE_HUNDRED)
    private final Integer experience;

    @NotNull(message = ValidationList.BIRTHDATE_REQUIRED)
    @Past(message = ValidationList.WRONG_BIRTHDATE)
    private final LocalDate birthDate;
}
