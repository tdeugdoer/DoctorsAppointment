package com.tserashkevich.doctorservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LogList {
    public static final String NOT_FOUND_ERROR = "Not found exception thrown: {}";
    public static final String PHONE_ALREADY_EXIST_ERROR = "Phone already exist exception thrown: {}";
    public static final String METHOD_ARGUMENT_ERROR = "Not valid method argument exception thrown: {}";
    public static final String CONSTRAINT_VIOLATION_ERROR = "Failed parameter verification exception thrown: {}";
    public static final String CREATE_DOCTOR = "Doctor created with ID: {}";
    public static final String EDIT_DOCTOR = "Doctor edited with ID: {}";
    public static final String DELETE_DOCTOR = "Doctor deleted with ID: {}";
    public static final String FIND_ALL_DOCTORS = "Found all doctors";
    public static final String FIND_DOCTOR = "Found doctors with ID: {}";
}