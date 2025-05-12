package com.tserashkevich.medicalhistoryservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationList {
    public final String APPOINTMENT_ID_REQUIRED = "Приём обязателен";
    public final String DOCTOR_ID_REQUIRED = "Доктор обязателен";
    public final String PATIENT_ID_REQUIRED = "Пациент обязателен";
    public final String DIAGNOSIS_REQUIRED = "Диагноз обязателен";
    public final String WRONG_UUID_FORMAT = "Неверный uuid формат";
    public final String DATE_REQUIRED = "Дата обязательна";
    public final String WRONG_DATE = "Неверная дата";

}