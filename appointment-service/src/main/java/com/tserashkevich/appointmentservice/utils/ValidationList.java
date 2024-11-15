package com.tserashkevich.appointmentservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationList {
    public static final String SERVICE_ID_REQUIRED = "{service.required}";
    public static final String DOCTOR_ID_REQUIRED = "{doctor.required}";
    public static final String PATIENT_ID_REQUIRED = "{patient.required}";
    public static final String WRONG_UUID_FORMAT = "{wrong.uuid.format}";
    public static final String DATE_REQUIRED = "{date.required}";
    public static final String WRONG_DATE = "{wrong.date}";
}