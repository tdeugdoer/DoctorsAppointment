package com.tserashkevich.medicalhistoryservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationList {
    public final String APPOINTMENT_ID_REQUIRED = "{appointment.required}";
    public final String DOCTOR_ID_REQUIRED = "{doctor.required}";
    public final String PATIENT_ID_REQUIRED = "{patient.required}";
    public final String DIAGNOSIS_REQUIRED = "{diagnosis.required}";
    public final String WRONG_UUID_FORMAT = "{wrong.uuid.format}";
    public final String DATE_REQUIRED = "{date.required}";
    public final String WRONG_DATE = "{wrong.date}";
}