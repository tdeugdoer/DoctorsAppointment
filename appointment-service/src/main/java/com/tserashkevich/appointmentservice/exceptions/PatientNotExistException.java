package com.tserashkevich.appointmentservice.exceptions;

import com.tserashkevich.appointmentservice.utils.ExceptionList;

public class PatientNotExistException extends RuntimeException {
    public PatientNotExistException() {
        super(ExceptionList.PATIENT_NOT_EXIST.getValue());
    }
}