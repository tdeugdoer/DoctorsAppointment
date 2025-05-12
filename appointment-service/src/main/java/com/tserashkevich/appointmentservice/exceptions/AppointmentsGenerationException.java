package com.tserashkevich.appointmentservice.exceptions;

import com.tserashkevich.appointmentservice.utils.ExceptionList;

public class AppointmentsGenerationException extends RuntimeException {
    public AppointmentsGenerationException() {
        super(ExceptionList.APPOINTMENTS_GENERATE_FAIL);
    }

}