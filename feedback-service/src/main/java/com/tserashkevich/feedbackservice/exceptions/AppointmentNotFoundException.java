package com.tserashkevich.feedbackservice.exceptions;


import com.tserashkevich.feedbackservice.utils.ExceptionList;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException() {
        super(ExceptionList.APPOINTMENT_NOT_FOUND.getValue());
    }
}
