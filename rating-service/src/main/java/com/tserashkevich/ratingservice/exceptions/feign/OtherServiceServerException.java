package com.tserashkevich.ratingservice.exceptions.feign;


import com.tserashkevich.ratingservice.utils.ExceptionList;

public class OtherServiceServerException extends RuntimeException {
    public OtherServiceServerException() {
        super(ExceptionList.SERVER_OTHER_SERVICE.getValue());
    }
}
