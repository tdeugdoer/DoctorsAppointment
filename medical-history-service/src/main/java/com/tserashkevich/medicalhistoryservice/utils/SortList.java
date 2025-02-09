package com.tserashkevich.medicalhistoryservice.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum SortList {
    ID_ASC(Sort.by(Sort.Direction.ASC, "id")),
    ID_DESC(Sort.by(Sort.Direction.DESC, "id")),
    DATE_OF_VISIT_ASC(Sort.by(Sort.Direction.ASC, "date_of_visit")),
    DATE_OF_VISIT_DESC(Sort.by(Sort.Direction.DESC, "date_of_visit"));


    private final Sort value;
}
