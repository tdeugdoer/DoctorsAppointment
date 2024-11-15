package com.tserashkevich.appointmentservice.exceptions;

import com.tserashkevich.appointmentservice.utils.ExceptionList;

public class DoctorNotExistException extends RuntimeException {
    public DoctorNotExistException() {
        super(ExceptionList.DOCTOR_NOT_EXIST.getValue());
    }
}