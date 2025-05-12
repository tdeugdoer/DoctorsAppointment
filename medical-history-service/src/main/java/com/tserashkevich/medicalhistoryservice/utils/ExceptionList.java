package com.tserashkevich.medicalhistoryservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionList {
    public final String MEDICAL_RECORD_NOT_FOUND = "Медицинская запись не найдена";
    public final String MEDICAL_RECORD_MISSING_FILE_KEY = "Medical record is missing the file key";
    public final String APPOINTMENT_NOT_FOUND = "Запись, для которой создаётся медицинская запись, не существует";
    public final String FILE_PROCESSING_ERROR = "Ошибка обработки файла";
    public final String BAD_FILE = "Проблема с файлом";
    public final String BAD_REQUEST_OTHER_SERVICE = "Невозможно получить ответ от другого сервиса (Bad request)";
    public final String NOT_FOUND_OTHER_SERVICE = "Объект не найден при запросе к другому сервису (Not found)";
    public final String SERVER_OTHER_SERVICE = "Невозможно получить ответ от другого сервиса (Server Error)";

}
