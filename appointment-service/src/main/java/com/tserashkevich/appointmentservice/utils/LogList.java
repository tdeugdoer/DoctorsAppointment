package com.tserashkevich.appointmentservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LogList {
    public final String NOT_FOUND_ERROR = "Not found exception thrown: {}";
    public final String BAD_REQUEST_ERROR = "Bed request exception: {}";
    public final String METHOD_ARGUMENT_ERROR = "Not valid method argument exception thrown: {}";
    public final String CONSTRAINT_VIOLATION_ERROR = "Failed parameter verification exception thrown: {}";
    public final String BAD_REQUEST_OTHER_SERVICE = "Bad request, other service: {}";
    public final String NOT_FOUND_OTHER_SERVICE = "Not found, other service: {}";
    public final String SERVER_OTHER_SERVICE = "Server, other service: {}";
    public final String CREATE_APPOINTMENT = "Appointment created with ID: {}";
    public final String DELETE_APPOINTMENT = "Appointment deleted with ID: {}";
    public final String FIND_ALL_APPOINTMENTS = "Found all appointments";
    public final String FIND_APPOINTMENT = "Found appointments with ID: {}";
    public final String SEARCH_APPOINTMENT = "Search with searchline: {}";
    public final String FREE_APPOINTMENT = "Free appointment with id {}";
    public final String BOOK_APPOINTMENT = "Book appointment with id {} by patient {}";
    public final String CHECK_IN_APPOINTMENT = "Check in appointment with id {}";
    public final String IN_PROGRESS_APPOINTMENT = "In progress appointment with id {}";
    public final String COMPLETE_APPOINTMENT = "Complete appointment with id {}";
    public final String NO_SHOW_APPOINTMENT = "No-show appointment with id {}";
}