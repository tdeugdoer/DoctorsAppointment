package com.tserashkevich.feedbackservice.exceptions.feign;


import com.tserashkevich.feedbackservice.utils.ExceptionList;

public class OtherServiceServerException extends RuntimeException {
    public OtherServiceServerException() {
        super(ExceptionList.SERVER_OTHER_SERVICE.getValue());
    }
}
