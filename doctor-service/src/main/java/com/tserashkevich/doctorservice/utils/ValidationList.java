package com.tserashkevich.doctorservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationList {
    public final String NAME_REQUIRED = "Имя обязательно";
    public final String WRONG_MAX_NAME_LENGTH = "Неверная длина имени";
    public final String SURNAME_REQUIRED = "Фамилия обязательна";
    public final String WRONG_MAX_SURNAME_LENGTH = "Неверная длина фамилии";
    public final String WRONG_MAX_PATRONYMIC_LENGTH = "Неверная длина отчества";
    public final String SPECIALIZATION_REQUIRED = "Специализация обязательна";
    public final String WRONG_SPECIALIZATION = "Неверная специализация";
    public final String GENDER_REQUIRED = "Пол обязательный";
    public final String WRONG_GENDER = "Неверный пол";
    public final String PHONE_REQUIRED = "Номер телефона обязательный";
    public final String WRONG_PHONE_FORMAT = "Неверный формат номера телефона";
    public final String EXPERIENCE_LESS_ZERO = "Опыт должен быть больше нуля";
    public final String EXPERIENCE_MORE_HUNDRED = "Опыт должен быть меньше ста";
    public final String BIRTHDATE_REQUIRED = "Дата рождения обязательна";
    public final String WRONG_BIRTHDATE = "Неверная дата рождения";

}