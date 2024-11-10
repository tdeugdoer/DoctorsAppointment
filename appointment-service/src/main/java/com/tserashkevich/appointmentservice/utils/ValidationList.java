package com.tserashkevich.appointmentservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationList {
    public static final String DOCTOR_ID_REQUIRED = "{doctor.required}";
    public static final String DOCTOR_NOT_EXIST = "{doctor.not.exist}";
    public static final String PATIENT_ID_REQUIRED = "{patient.required}";
    public static final String PATIENT_NOT_EXIST = "{patient.not.exist}";
    public static final String WRONG_UUID_FORMAT = "{wrong.uuid.format}";
    public static final String DATE_REQUIRED = "{date.required}";
    public static final String WRONG_DATE = "{wrong.date}";
    public static final String PRICE_REQUIRED = "{price.required}";
    public static final String LESS_ZERO = "{less.zero}";
}