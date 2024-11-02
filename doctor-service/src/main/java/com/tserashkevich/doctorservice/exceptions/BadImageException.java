package com.tserashkevich.doctorservice.exceptions;


import com.tserashkevich.doctorservice.utils.ExceptionList;

public class BadImageException extends RuntimeException {
    public BadImageException() {
        super(ExceptionList.BAD_IMAGE.getValue());
    }
}
