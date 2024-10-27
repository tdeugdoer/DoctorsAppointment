package com.tserashkevich.doctorservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PatternList {
    public static final String PHONE_PATTERN = "\\+375\\d{9}";
    public static final String GENDER_PATTERN = "^(?:Men|Women|Other)$";
    public static final String SPECIALIZATION_PATTERN = "^(?:Therapy|Surgery|Pediatrics|Neurology|Dentistry|Gynecology|Dermatological|Other)$";
}
