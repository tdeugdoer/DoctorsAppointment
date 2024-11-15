package com.tserashkevich.catalogservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LogList {
    public static final String NOT_FOUND_ERROR = "Not found exception thrown: {}";
    public static final String METHOD_ARGUMENT_ERROR = "Not valid method argument exception thrown: {}";
    public static final String CONSTRAINT_VIOLATION_ERROR = "Failed parameter verification exception thrown: {}";
    public static final String MESSAGE_NOT_READABLE_ERROR = "Message not readable exception thrown: {}";
    public static final String CREATE_SERVICE = "Service created with ID: {}";
    public static final String EDIT_SERVICE = "Service edited with ID: {}";
    public static final String DELETE_SERVICE = "Service deleted with ID: {}";
    public static final String FIND_ALL_SERVICES = "Found all services";
    public static final String FIND_SERVICE = "Found services with ID: {}";
    public static final String SEARCH_SERVICE = "Search service with searchline: {}";
    public static final String EXIST_SERVICE = "Check exist service with ID: {}";
}