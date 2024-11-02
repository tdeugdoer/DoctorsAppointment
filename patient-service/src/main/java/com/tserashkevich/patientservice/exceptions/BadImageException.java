package com.tserashkevich.patientservice.exceptions;

import com.tserashkevich.patientservice.utils.ExceptionList;

public class BadImageException extends RuntimeException {
    public BadImageException() {
        super(ExceptionList.BAD_IMAGE.getValue());
    }
}
