package com.tserashkevich.appointmentservice.exceptions.feign;

import com.tserashkevich.appointmentservice.utils.ExceptionList;

public class OtherServiceNotFoundException extends RuntimeException {
    public OtherServiceNotFoundException() {
        super(ExceptionList.NOT_FOUND_OTHER_SERVICE.getValue());
    }
}
