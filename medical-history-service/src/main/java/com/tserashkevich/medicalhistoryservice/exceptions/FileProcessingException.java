package com.tserashkevich.medicalhistoryservice.exceptions;

import com.tserashkevich.medicalhistoryservice.utils.ExceptionList;

public class FileProcessingException extends RuntimeException {
    public FileProcessingException() {
        super(ExceptionList.FILE_PROCESSING_ERROR.getValue());
    }
}
