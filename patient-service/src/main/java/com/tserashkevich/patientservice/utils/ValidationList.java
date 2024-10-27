package com.tserashkevich.patientservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationList {
    public static final String NAME_REQUIRED = "{name.required}";
    public static final String WRONG_MAX_NAME_LENGTH = "{wrong.max.name.length}";
    public static final String SURNAME_REQUIRED = "{surname.required}";
    public static final String WRONG_MAX_SURNAME_LENGTH = "{wrong.max.surname.length}";
    public static final String WRONG_MAX_PATRONYMIC_LENGTH = "{wrong.max.patronymic.length}";
    public static final String GENDER_REQUIRED = "{gender.required}";
    public static final String WRONG_GENDER = "{wrong.gender}";
    public static final String PHONE_REQUIRED = "{phone.required}";
    public static final String WRONG_PHONE_FORMAT = "{wrong.phone.format}";
    public static final String BIRTHDATE_REQUIRED = "{birthdate.required}";
    public static final String WRONG_BIRTHDATE = "{wrong.birthdate}";
}