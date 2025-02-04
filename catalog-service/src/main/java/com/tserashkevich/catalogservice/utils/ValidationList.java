package com.tserashkevich.catalogservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationList {
    public final String NAME_REQUIRED = "{name.required}";
    public final String WRONG_MAX_NAME_LENGTH = "{wrong.max.name.length}";
    public final String SPECIALIZATION_REQUIRED = "{specialization.required}";
    public final String WRONG_SPECIALIZATION = "{wrong.specialization}";
    public final String PRICE_REQUIRED = "{price.required}";
    public final String LESS_ZERO = "{less.zero}";

}