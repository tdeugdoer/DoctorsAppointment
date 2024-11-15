package com.tserashkevich.catalogservice.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum SortList {
    ID_ASC(Sort.by(Sort.Direction.ASC, "id")),
    ID_DESC(Sort.by(Sort.Direction.DESC, "id")),
    BIRTH_PRICE_ASC(Sort.by(Sort.Direction.ASC, "price")),
    BIRTH_PRICE_DESC(Sort.by(Sort.Direction.DESC, "price"));

    private final Sort value;
}
