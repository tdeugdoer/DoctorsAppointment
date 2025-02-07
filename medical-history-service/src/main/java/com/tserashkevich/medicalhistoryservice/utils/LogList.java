package com.tserashkevich.medicalrecordservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LogList {
    public final String NOT_FOUND_ERROR = "Not found exception thrown: {}";
    public final String METHOD_ARGUMENT_ERROR = "Not valid method argument exception thrown: {}";
    public final String CONSTRAINT_VIOLATION_ERROR = "Failed parameter verification exception thrown: {}";
    public final String CREATE_MEDICAL_RECORD = "Medical record created with ID: {}";
    public final String EDIT_MEDICAL_RECORD = "Medical record edited with ID: {}";
    public final String DELETE_MEDICAL_RECORD = "Medical record deleted with ID: {}";
    public final String FIND_ALL_MEDICAL_RECORDS = "Found all medical records";
    public final String FIND_MEDICAL_RECORD = "Found doctors with ID: {}";
    public final String FILE_PROCESSING_ERROR = "File processing error";
    public final String BAD_FILE = "Bad file";
    public final String UPLOAD_FILE = "File uploaded with key: {}";
    public final String UPDATE_FILE = "File updated with key: {}";
    public final String DELETE_FILE = "File deleted with key: {}";
    public final String GET_FILE = "File got with key: {}";
}