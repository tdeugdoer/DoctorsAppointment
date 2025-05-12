package com.tserashkevich.appointmentservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationList {
    public final String SERVICE_ID_REQUIRED = "Услуга обязательна";
    public final String DOCTOR_ID_REQUIRED = "Доктор обязателен";
    public final String PATIENT_ID_REQUIRED = "Пациент обязателен";
    public final String DOCTOR_WORK_DAY_ID_REQUIRED = "Id рабочего дня врача обязательна";
    public final String WRONG_UUID_FORMAT = "Неверный uuid формат";
    public final String DATE_REQUIRED = "Дата обязательна";
    public final String WRONG_DATE = "Неверная дата";
    public final String WRONG_TIME = "Время должно быть между 08:00 и 21:00";

}