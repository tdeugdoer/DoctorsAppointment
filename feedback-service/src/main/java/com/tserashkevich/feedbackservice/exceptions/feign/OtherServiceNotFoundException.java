package com.tserashkevich.feedbackservice.exceptions.feign;


import com.tserashkevich.feedbackservice.utils.ExceptionList;

public class OtherServiceNotFoundException extends RuntimeException {
    public OtherServiceNotFoundException() {
        super(ExceptionList.NOT_FOUND_OTHER_SERVICE);
    }

}
