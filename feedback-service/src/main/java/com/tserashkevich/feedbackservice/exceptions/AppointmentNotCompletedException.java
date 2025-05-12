package com.tserashkevich.feedbackservice.exceptions;


import com.tserashkevich.feedbackservice.utils.ExceptionList;

public class AppointmentNotCompletedException extends RuntimeException {
    public AppointmentNotCompletedException() {
        super(ExceptionList.APPOINTMENT_NOT_COMPLETED);
    }

}