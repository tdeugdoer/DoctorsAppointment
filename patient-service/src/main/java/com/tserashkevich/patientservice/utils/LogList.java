package com.tserashkevich.patientservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LogList {
    public static final String NOT_FOUND_ERROR = "Not found exception thrown: {}";
    public static final String PHONE_ALREADY_EXIST_ERROR = "Phone already exist exception thrown: {}";
    public static final String METHOD_ARGUMENT_ERROR = "Not valid method argument exception thrown: {}";
    public static final String CONSTRAINT_VIOLATION_ERROR = "Failed parameter verification exception thrown: {}";
    public static final String CREATE_PATIENT = "Patient created with ID: {}";
    public static final String EDIT_PATIENT = "Patient edited with ID: {}";
    public static final String DELETE_PATIENT = "Patient deleted with ID: {}";
    public static final String FIND_ALL_PATIENTS = "Found all patients";
    public static final String FIND_PATIENT = "Found patients with ID: {}";
    public static final String IMAGE_PROCESSING_ERROR = "Image processing error";
    public static final String BAD_IMAGE = "Bad image";
    public static final String UPLOAD_IMAGE = "Image uploaded with key: {}";
    public static final String UPDATE_IMAGE = "Image updated with key: {}";
    public static final String DELETE_IMAGE = "Image deleted with key: {}";
    public static final String GET_IMAGE = "Image got with key: {}";
}