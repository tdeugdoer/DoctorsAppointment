package com.tserashkevich.medicalhistoryservice.exceptions.feign;


import com.tserashkevich.medicalhistoryservice.utils.ExceptionList;

public class OtherServiceBadRequestException extends RuntimeException {
    public OtherServiceBadRequestException() {
        super(ExceptionList.BAD_REQUEST_OTHER_SERVICE.getValue());
    }
}
