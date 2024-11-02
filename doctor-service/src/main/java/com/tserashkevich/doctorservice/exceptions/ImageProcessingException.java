package com.tserashkevich.doctorservice.exceptions;


import com.tserashkevich.doctorservice.utils.ExceptionList;

public class ImageProcessingException extends RuntimeException {
    public ImageProcessingException() {
        super(ExceptionList.IMAGE_PROCESSING_ERROR.getValue());
    }
}
