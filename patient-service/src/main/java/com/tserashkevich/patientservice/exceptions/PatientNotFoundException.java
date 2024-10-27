package com.tserashkevich.patientservice.exceptions;

import com.tserashkevich.patientservice.utils.ExceptionList;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException() {
        super(ExceptionList.PATIENT_NOT_FOUND.getValue());
    }
}
