package com.tserashkevich.appointmentservice.exceptions;

import com.tserashkevich.appointmentservice.utils.ExceptionList;

public class ServiceNotExistException extends RuntimeException {
    public ServiceNotExistException() {
        super(ExceptionList.SERVICE_NOT_EXIST.getValue());
    }
}