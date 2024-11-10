package com.tserashkevich.appointmentservice.exceptions;

import com.tserashkevich.appointmentservice.utils.ExceptionList;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException() {
        super(ExceptionList.APPOINTMENT_NOT_FOUND.getValue());
    }
}