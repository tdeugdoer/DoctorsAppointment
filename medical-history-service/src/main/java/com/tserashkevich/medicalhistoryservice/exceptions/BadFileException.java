package com.tserashkevich.medicalhistoryservice.exceptions;

import com.tserashkevich.medicalhistoryservice.utils.ExceptionList;

public class BadFileException extends RuntimeException {
    public BadFileException() {
        super(ExceptionList.BAD_FILE);
    }

}
