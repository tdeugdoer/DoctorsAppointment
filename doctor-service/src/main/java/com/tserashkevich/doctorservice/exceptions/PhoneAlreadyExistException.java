package com.tserashkevich.doctorservice.exceptions;


import com.tserashkevich.doctorservice.utils.ExceptionList;

public class PhoneAlreadyExistException extends RuntimeException {
    public PhoneAlreadyExistException() {
        super(ExceptionList.PHONE_NUMBER_ALREADY_EXIST.getValue());
    }
}
