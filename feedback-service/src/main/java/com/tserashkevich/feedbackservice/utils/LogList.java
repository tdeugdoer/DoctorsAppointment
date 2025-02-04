package com.tserashkevich.feedbackservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LogList {
    public static final String NOT_FOUND_ERROR = "Not Found exception thrown: {}";
    public static final String METHOD_ARGUMENT_ERROR = "Not Valid Method Argument exception thrown: {}";
    public static final String CONSTRAINT_VIOLATION_ERROR = "Failed parameter verification exception thrown: {}";
    public static final String CREATE_FEEDBACK = "Feedback created with ID: {}";
    public static final String EDIT_FEEDBACK = "Feedback edited with ID: {}";
    public static final String DELETE_FEEDBACK = "Feedback deleted with ID: {}";
    public static final String FIND_ALL_FEEDBACKS = "Found all feedbacks";
    public static final String FIND_FEEDBACK = "Found Feedback with ID: {}";
    public static final String COUNT_AVG_FEEDBACK = "Count avg feedback by targetId: {}";
    public static final String FIND_FEEDBACKS = "Find feedbacks by targetId: {}";
    public static final String FEEDBACK_EXIST = "User has already rated the ride";
    public static final String KAFKA_SEND_MESSAGE = "Kafka send message: {}";
    public static final String KAFKA_SEND_MESSAGE_FAIL = "Kafka send message fail: {} ({})";
    public static final String BAD_REQUEST_OTHER_SERVICE = "Bad request, other service: {}";
    public static final String NOT_FOUND_OTHER_SERVICE = "Not found, other service: {}";
    public static final String SERVER_OTHER_SERVICE = "Server, other service: {}";
}