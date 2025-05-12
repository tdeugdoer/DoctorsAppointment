package com.tserashkevich.catalogservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PatternList {
    public final String SPECIALIZATION_PATTERN =
            "^(?:Терапевт|Хирургия|Педиатрия|Неврология|Стоматология|Гинекология|Дерматология|Другое)$";

}
