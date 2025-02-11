package com.tserashkevich.medicalhistoryservice.exceptions.feign;


import com.tserashkevich.medicalhistoryservice.utils.ExceptionList;

public class OtherServiceNotFoundException extends RuntimeException {
    public OtherServiceNotFoundException() {
        super(ExceptionList.NOT_FOUND_OTHER_SERVICE.getValue());
    }
}
