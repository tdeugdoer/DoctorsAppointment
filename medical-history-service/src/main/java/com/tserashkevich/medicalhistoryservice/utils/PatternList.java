package com.tserashkevich.medicalhistoryservice.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PatternList {
    public final String UUID_PATTERN = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
}
