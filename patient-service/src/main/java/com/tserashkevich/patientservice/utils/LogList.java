package com.tserashkevich.patientservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LogList {
    public final String NOT_FOUND_ERROR = "Not found exception thrown: {}";
    public final String PHONE_ALREADY_EXIST_ERROR = "Phone already exist exception thrown: {}";
    public final String METHOD_ARGUMENT_ERROR = "Not valid method argument exception thrown: {}";
    public final String CONSTRAINT_VIOLATION_ERROR = "Failed parameter verification exception thrown: {}";
    public final String CREATE_PATIENT = "Patient created with ID: {}";
    public final String EDIT_PATIENT = "Patient edited with ID: {}";
    public final String DELETE_PATIENT = "Patient deleted with ID: {}";
    public final String FIND_ALL_PATIENTS = "Found all patients";
    public final String FIND_PATIENT = "Found patients with ID: {}";
    public final String IMAGE_PROCESSING_ERROR = "Image processing error";
    public final String BAD_IMAGE = "Bad image";
    public final String UPLOAD_IMAGE = "Image uploaded with key: {}";
    public final String UPDATE_IMAGE = "Image updated with key: {}";
    public final String DELETE_IMAGE = "Image deleted with key: {}";
    public final String GET_IMAGE = "Image got with key: {}";
    public final String SEARCH_PATIENT = "Search patient with searchline: {}";
}