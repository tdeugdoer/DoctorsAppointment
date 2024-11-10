package com.tserashkevich.appointmentservice.exceptions.feign;


import com.tserashkevich.appointmentservice.utils.ExceptionList;

public class OtherServiceServerException extends RuntimeException {
    public OtherServiceServerException() {
        super(ExceptionList.SERVER_OTHER_SERVICE.getValue());
    }
}
