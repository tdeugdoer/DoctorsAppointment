package com.tserashkevich.feedbackservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationList {
    public static final String APPOINTMENT_ID_REQUIRED = "{appointment.id.required}";
    public static final String WRONG_UUID_FORMAT = "{wrong.uuid.format}";
    public static final String RATING_REQUIRED = "{rating.required}";
    public static final String LESS_ONE = "{less.one}";
    public static final String MORE_FIVE = "{more.five}";
}
