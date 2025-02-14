package com.tserashkevich.appointmentservice.exceptions;

import com.tserashkevich.appointmentservice.utils.ExceptionList;

public class AppointmentAlreadyCompletedException extends RuntimeException {
    public AppointmentAlreadyCompletedException() {
        super(ExceptionList.APPOINTMENT_ALREADY_COMPLETED.getValue());
    }
}