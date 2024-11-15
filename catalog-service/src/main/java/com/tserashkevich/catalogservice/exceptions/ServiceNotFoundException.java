package com.tserashkevich.catalogservice.exceptions;


import com.tserashkevich.catalogservice.utils.ExceptionList;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException() {
        super(ExceptionList.SERVICE_NOT_FOUND.getValue());
    }
}
