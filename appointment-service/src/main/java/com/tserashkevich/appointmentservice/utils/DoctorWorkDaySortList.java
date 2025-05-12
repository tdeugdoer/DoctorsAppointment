package com.tserashkevich.appointmentservice.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum DoctorWorkDaySortList {
    ID_ASC(Sort.by(Sort.Direction.ASC, "id")),
    ID_DESC(Sort.by(Sort.Direction.DESC, "id")),
    DATE_ASC(Sort.by(Sort.Direction.ASC, "date")),
    DATE_DESC(Sort.by(Sort.Direction.DESC, "date"));

    private final Sort value;
}
