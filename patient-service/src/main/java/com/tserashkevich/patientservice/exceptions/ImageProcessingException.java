package com.tserashkevich.patientservice.exceptions;

import com.tserashkevich.patientservice.utils.ExceptionList;

public class ImageProcessingException extends RuntimeException {
    public ImageProcessingException() {
        super(ExceptionList.IMAGE_PROCESSING_ERROR.getValue());
    }
}
