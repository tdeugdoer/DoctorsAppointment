package com.tserashkevich.feedbackservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionList {
    public final String FEEDBACK_NOT_FOUND = "Отзыв не найден";
    public final String FEEDBACK_EXIST = "Отзыв уже существует";
    public final String APPOINTMENT_NOT_FOUND = "Запись, для которой создаётся отзыв, не существует";
    public final String APPOINTMENT_NOT_COMPLETED = "Запись, для которой создаётся отзыв, не завершена";
    public final String BAD_REQUEST_OTHER_SERVICE = "Невозможно получить ответ от другого сервиса (Bad request)";
    public final String NOT_FOUND_OTHER_SERVICE = "Объект не найден при запросе к другому сервису (Not found)";
    public final String SERVER_OTHER_SERVICE = "Невозможно получить ответ от другого сервиса (Server Error)";

}

