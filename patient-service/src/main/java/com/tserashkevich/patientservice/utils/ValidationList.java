package com.tserashkevich.patientservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationList {
    public final String NAME_REQUIRED = "{name.required}";
    public final String WRONG_MAX_NAME_LENGTH = "{wrong.max.name.length}";
    public final String SURNAME_REQUIRED = "{surname.required}";
    public final String WRONG_MAX_SURNAME_LENGTH = "{wrong.max.surname.length}";
    public final String WRONG_MAX_PATRONYMIC_LENGTH = "{wrong.max.patronymic.length}";
    public final String GENDER_REQUIRED = "{gender.required}";
    public final String WRONG_GENDER = "{wrong.gender}";
    public final String PHONE_REQUIRED = "{phone.required}";
    public final String WRONG_PHONE_FORMAT = "{wrong.phone.format}";
    public final String BIRTHDATE_REQUIRED = "{birthdate.required}";
    public final String WRONG_BIRTHDATE = "{wrong.birthdate}";
}