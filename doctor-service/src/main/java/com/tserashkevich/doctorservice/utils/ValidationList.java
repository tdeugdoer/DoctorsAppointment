package com.tserashkevich.doctorservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationList {
    public static final String NAME_REQUIRED = "{name.required}";
    public static final String WRONG_MAX_NAME_LENGTH = "{wrong.max.name.length}";
    public static final String SURNAME_REQUIRED = "{surname.required}";
    public static final String WRONG_MAX_SURNAME_LENGTH = "{wrong.max.surname.length}";
    public static final String WRONG_MAX_PATRONYMIC_LENGTH = "{wrong.max.patronymic.length}";
    public static final String SPECIALIZATION_REQUIRED = "{specialization.required}";
    public static final String WRONG_SPECIALIZATION = "{wrong.specialization}";
    public static final String GENDER_REQUIRED = "{gender.required}";
    public static final String WRONG_GENDER = "{wrong.gender}";
    public static final String PHONE_REQUIRED = "{phone.required}";
    public static final String WRONG_PHONE_FORMAT = "{wrong.phone.format}";
    public static final String EXPERIENCE_LESS_ZERO = "{experience.less.zero}";
    public static final String EXPERIENCE_MORE_HUNDRED = "{experience.more.hundred}";
    public static final String BIRTHDATE_REQUIRED = "{birthdate.required}";
    public static final String WRONG_BIRTHDATE = "{wrong.birthdate}";
}