package com.tserashkevich.medicalhistoryservice.exceptions.feign;


import com.tserashkevich.medicalhistoryservice.utils.ExceptionList;

public class OtherServiceServerException extends RuntimeException {
    public OtherServiceServerException() {
        super(ExceptionList.SERVER_OTHER_SERVICE);
    }

}
