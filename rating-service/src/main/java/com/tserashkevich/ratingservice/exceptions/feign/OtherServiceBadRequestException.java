package com.tserashkevich.ratingservice.exceptions.feign;


import com.tserashkevich.ratingservice.utils.ExceptionList;

public class OtherServiceBadRequestException extends RuntimeException {
    public OtherServiceBadRequestException() {
        super(ExceptionList.BAD_REQUEST_OTHER_SERVICE.getValue());
    }
}
