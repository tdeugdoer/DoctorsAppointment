package com.tserashkevich.ratingservice.exceptions;


import com.tserashkevich.ratingservice.utils.ExceptionList;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException() {
        super(ExceptionList.APPOINTMENT_NOT_FOUND.getValue());
    }
}
