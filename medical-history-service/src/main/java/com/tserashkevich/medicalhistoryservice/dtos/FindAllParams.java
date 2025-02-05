package com.tserashkevich.medicalhistoryservice.dtos;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
@Builder
public class FindAllParams {
    private final Integer page;
    private final Integer limit;
    private final Sort sort;
}