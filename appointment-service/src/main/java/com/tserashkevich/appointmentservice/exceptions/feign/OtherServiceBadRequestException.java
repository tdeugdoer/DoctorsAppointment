package com.tserashkevich.appointmentservice.exceptions.feign;


import com.tserashkevich.appointmentservice.utils.ExceptionList;

public class OtherServiceBadRequestException extends RuntimeException {
    public OtherServiceBadRequestException() {
        super(ExceptionList.BAD_REQUEST_OTHER_SERVICE.getValue());
    }
}
