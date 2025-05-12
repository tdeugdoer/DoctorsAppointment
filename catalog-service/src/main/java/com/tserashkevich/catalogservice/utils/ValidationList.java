package com.tserashkevich.catalogservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationList {
    public final String NAME_REQUIRED = "Имя обязательно";
    public final String WRONG_MAX_NAME_LENGTH = "Неверная длина имени";
    public final String SPECIALIZATION_REQUIRED = "Специализация обязательна";
    public final String WRONG_SPECIALIZATION = "Неверная специализация";
    public final String PRICE_REQUIRED = "Цена обязательна";
    public final String DURATION_REQUIRED = "Продолжительность обязательна";
    public final String LESS_ZERO = "Цена меньше нуля";

}