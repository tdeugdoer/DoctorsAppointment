package com.tserashkevich.appointmentservice.exceptions;

import com.tserashkevich.appointmentservice.utils.ExceptionList;

public class AppointmentAlreadyNoShowException extends RuntimeException {
    public AppointmentAlreadyNoShowException() {
        super(ExceptionList.APPOINTMENT_ALREADY_NO_SHOW);
    }

}