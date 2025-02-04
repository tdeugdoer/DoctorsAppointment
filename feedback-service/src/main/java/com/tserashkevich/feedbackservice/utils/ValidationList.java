package com.tserashkevich.feedbackservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationList {
    public final String APPOINTMENT_ID_REQUIRED = "{appointment.id.required}";
    public final String WRONG_UUID_FORMAT = "{wrong.uuid.format}";
    public final String RATING_REQUIRED = "{rating.required}";
    public final String LESS_ONE = "{less.one}";
    public final String MORE_FIVE = "{more.five}";
}
