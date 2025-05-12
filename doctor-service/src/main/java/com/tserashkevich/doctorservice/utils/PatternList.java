package com.tserashkevich.doctorservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PatternList {
    public final String PHONE_PATTERN = "\\+375\\d{9}";
    public final String GENDER_PATTERN = "^(?:Мужской|Женский|Другой)$";
    public final String SPECIALIZATION_PATTERN =
            "^(?:Терапевт|Хирургия|Педиатрия|Неврология|Стоматология|Гинекология|Дерматология|Другое)$";

}
