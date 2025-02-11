package com.tserashkevich.medicalhistoryservice.exceptions;


import com.tserashkevich.medicalhistoryservice.utils.ExceptionList;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException() {
        super(ExceptionList.APPOINTMENT_NOT_FOUND.getValue());
    }
}
