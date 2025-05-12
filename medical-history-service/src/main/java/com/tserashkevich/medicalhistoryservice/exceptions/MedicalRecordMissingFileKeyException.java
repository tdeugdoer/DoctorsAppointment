package com.tserashkevich.medicalhistoryservice.exceptions;

import com.tserashkevich.medicalhistoryservice.utils.ExceptionList;

public class MedicalRecordMissingFileKeyException extends RuntimeException {
    public MedicalRecordMissingFileKeyException() {
        super(ExceptionList.MEDICAL_RECORD_MISSING_FILE_KEY);
    }

}
