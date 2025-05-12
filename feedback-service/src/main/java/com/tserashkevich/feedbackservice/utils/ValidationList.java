package com.tserashkevich.feedbackservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationList {
    public final String APPOINTMENT_ID_REQUIRED = "ID приёма обязательно";
    public final String WRONG_UUID_FORMAT = "Неверный формат UUID";
    public final String RATING_REQUIRED = "Оценка обязательна";
    public final String LESS_ONE = "Оценка должна быть больше или равна 1";
    public final String MORE_FIVE = "Оценка должна быть меньше или равна 5";

}