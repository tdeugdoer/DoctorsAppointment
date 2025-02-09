package com.tserashkevich.medicalhistoryservice.exceptions;

import com.tserashkevich.medicalhistoryservice.utils.ExceptionList;

public class MedicalRecordNotFoundException extends RuntimeException {
    public MedicalRecordNotFoundException() {
        super(ExceptionList.MEDICAL_RECORD_NOT_FOUND.getValue());
    }
}
