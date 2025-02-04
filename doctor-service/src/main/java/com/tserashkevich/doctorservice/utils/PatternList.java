package com.tserashkevich.doctorservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PatternList {
    public final String PHONE_PATTERN = "\\+375\\d{9}";
    public final String GENDER_PATTERN = "^(?:Men|Women|Other)$";
    public final String SPECIALIZATION_PATTERN = "^(?:Therapy|Surgery|Pediatrics|Neurology|Dentistry|Gynecology|Dermatological|Other)$";
}
