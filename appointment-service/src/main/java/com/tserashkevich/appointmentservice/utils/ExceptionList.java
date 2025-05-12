package com.tserashkevich.appointmentservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionList {
    public final String APPOINTMENT_NOT_FOUND = "Запись не найден";
    public final String DOCTOR_WORK_DAY_NOT_FOUND = "Рабочий день врача не найден";
    public final String APPOINTMENT_ALREADY_COMPLETED = "Приём уже завершён";
    public final String APPOINTMENT_ALREADY_NO_SHOW = "На прием никто не пришел";
    public final String BAD_REQUEST_OTHER_SERVICE = "Невозможно получить ответ от другого сервиса (Bad request)";
    public final String NOT_FOUND_OTHER_SERVICE = "Объект не найден при запросе к другому сервису (Not found)";
    public final String SERVER_OTHER_SERVICE = "Невозможно получить ответ от другого сервиса (Server Error)";
    public final String SERVICE_NOT_EXIST = "Услуги не существует";
    public final String PATIENT_NOT_EXIST = "Пациент не существует";
    public final String DOCTOR_NOT_EXIST = "Врач не существует";
    public final String DOCTOR_NOT_MATCH_SERVICE_EXIST = "Специальность врача не соответствует услуге";
    public final String APPOINTMENTS_GENERATE_FAIL = "Ошибка при генерации записей";

}
