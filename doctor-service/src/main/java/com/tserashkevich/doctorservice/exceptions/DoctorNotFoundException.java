package com.tserashkevich.doctorservice.exceptions;


import com.tserashkevich.doctorservice.utils.ExceptionList;

public class DoctorNotFoundException extends RuntimeException {
    public DoctorNotFoundException() {
        super(ExceptionList.DOCTOR_NOT_FOUND.getValue());
    }
}
