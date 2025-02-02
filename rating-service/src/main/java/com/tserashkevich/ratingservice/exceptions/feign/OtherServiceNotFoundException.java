package com.tserashkevich.ratingservice.exceptions.feign;


import com.tserashkevich.ratingservice.utils.ExceptionList;

public class OtherServiceNotFoundException extends RuntimeException {
    public OtherServiceNotFoundException() {
        super(ExceptionList.NOT_FOUND_OTHER_SERVICE.getValue());
    }
}
