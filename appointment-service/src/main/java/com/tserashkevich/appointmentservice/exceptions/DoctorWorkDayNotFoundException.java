package com.tserashkevich.appointmentservice.exceptions;

import com.tserashkevich.appointmentservice.utils.ExceptionList;

public class DoctorWorkDayNotFoundException extends RuntimeException {
    public DoctorWorkDayNotFoundException() {
        super(ExceptionList.DOCTOR_WORK_DAY_NOT_FOUND);
    }

}