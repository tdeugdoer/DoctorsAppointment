package com.tserashkevich.patientservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionList {
    public final String PATIENT_NOT_FOUND = "Пациент не найден";
    public final String PHONE_NUMBER_ALREADY_EXIST = "Номер телефона уже существует";
    public final String IMAGE_PROCESSING_ERROR = "Ошибка обработки изображения";
    public final String BAD_IMAGE = "Плохая картинка";

}
