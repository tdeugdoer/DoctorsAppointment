package com.tserashkevich.patientservice.exceptions;

import com.tserashkevich.patientservice.utils.ExceptionList;

public class PhoneAlreadyExistException extends RuntimeException {
    public PhoneAlreadyExistException() {
        super(ExceptionList.PHONE_NUMBER_ALREADY_EXIST.getValue());
    }
}
