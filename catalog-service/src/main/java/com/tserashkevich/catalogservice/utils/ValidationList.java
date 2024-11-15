package com.tserashkevich.catalogservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationList {
    public static final String NAME_REQUIRED = "{name.required}";
    public static final String WRONG_MAX_NAME_LENGTH = "{wrong.max.name.length}";
    public static final String SPECIALIZATION_REQUIRED = "{specialization.required}";
    public static final String WRONG_SPECIALIZATION = "{wrong.specialization}";
    public static final String PRICE_REQUIRED = "{price.required}";
    public static final String LESS_ZERO = "{less.zero}";

}