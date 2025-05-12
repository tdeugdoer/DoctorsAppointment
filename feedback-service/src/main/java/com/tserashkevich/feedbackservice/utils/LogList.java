package com.tserashkevich.feedbackservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LogList {
    public final String NOT_FOUND_ERROR = "Not Found exception thrown: {}";
    public final String METHOD_ARGUMENT_ERROR = "Not Valid Method Argument exception thrown: {}";
    public final String CONSTRAINT_VIOLATION_ERROR = "Failed parameter verification exception thrown: {}";
    public final String CREATE_FEEDBACK = "Feedback created with ID: {}";
    public final String EDIT_FEEDBACK = "Feedback edited with ID: {}";
    public final String DELETE_FEEDBACK = "Feedback deleted with ID: {}";
    public final String FIND_ALL_FEEDBACKS = "Found all feedbacks";
    public final String FIND_FEEDBACK = "Found Feedback with ID: {}";
    public final String COUNT_AVG_FEEDBACK = "Count avg feedback by targetId: {}";
    public final String FIND_FEEDBACKS = "Find feedbacks by targetId: {}";
    public final String FEEDBACK_EXIST = "User has already rated the ride";
    public final String KAFKA_SEND_MESSAGE = "Kafka send message: {}";
    public final String KAFKA_SEND_MESSAGE_FAIL = "Kafka send message fail: {} ({})";
    public final String BAD_REQUEST_OTHER_SERVICE = "Bad request, other service: {}";
    public final String NOT_FOUND_OTHER_SERVICE = "Not found, other service: {}";
    public final String SERVER_OTHER_SERVICE = "Server, other service: {}";

}