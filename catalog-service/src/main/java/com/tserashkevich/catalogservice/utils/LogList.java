package com.tserashkevich.catalogservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LogList {
    public final String NOT_FOUND_ERROR = "Not found exception thrown: {}";
    public final String METHOD_ARGUMENT_ERROR = "Not valid method argument exception thrown: {}";
    public final String CONSTRAINT_VIOLATION_ERROR = "Failed parameter verification exception thrown: {}";
    public final String MESSAGE_NOT_READABLE_ERROR = "Message not readable exception thrown: {}";
    public final String CREATE_SERVICE = "Service created with ID: {}";
    public final String EDIT_SERVICE = "Service edited with ID: {}";
    public final String DELETE_SERVICE = "Service deleted with ID: {}";
    public final String FIND_ALL_SERVICES = "Found all services";
    public final String FIND_SERVICE = "Found services with ID: {}";
    public final String SEARCH_SERVICE = "Search service with searchline: {}";
    public final String EXIST_SERVICE = "Check exist service with ID: {}";
}