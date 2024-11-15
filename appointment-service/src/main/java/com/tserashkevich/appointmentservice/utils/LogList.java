package com.tserashkevich.appointmentservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LogList {
    public static final String NOT_FOUND_ERROR = "Not found exception thrown: {}";
    public static final String NOT_MATCH_ERROR = "Not match exception: {}";
    public static final String METHOD_ARGUMENT_ERROR = "Not valid method argument exception thrown: {}";
    public static final String CONSTRAINT_VIOLATION_ERROR = "Failed parameter verification exception thrown: {}";
    public static final String BAD_REQUEST_OTHER_SERVICE = "Bad request, other service: {}";
    public static final String NOT_FOUND_OTHER_SERVICE = "Not found, other service: {}";
    public static final String SERVER_OTHER_SERVICE = "Server, other service: {}";
    public static final String CREATE_APPOINTMENT = "Appointment created with ID: {}";
    public static final String DELETE_APPOINTMENT = "Appointment deleted with ID: {}";
    public static final String FIND_ALL_APPOINTMENTS = "Found all appointments";
    public static final String FIND_APPOINTMENT = "Found appointments with ID: {}";
    public static final String SEARCH_APPOINTMENT = "Search with searchline: {}";
    public static final String BOOK_APPOINTMENT = "Book appointment with id {} by patient {}";
    public static final String COMPLETE_APPOINTMENT = "Complete appointment with id {}";
}