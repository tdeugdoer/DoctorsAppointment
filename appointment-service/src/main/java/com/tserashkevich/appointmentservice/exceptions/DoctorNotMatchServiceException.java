package com.tserashkevich.appointmentservice.exceptions;

import com.tserashkevich.appointmentservice.utils.ExceptionList;

public class DoctorNotMatchServiceException extends RuntimeException {
    public DoctorNotMatchServiceException() {
        super(ExceptionList.DOCTOR_NOT_MATCH_SERVICE_EXIST.getValue());
    }
}