package com.tserashkevich.catalogservice.dtos;

import com.tserashkevich.catalogservice.models.enums.Specialization;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;

@Getter
@Builder
public class FindAllParams {
    private final Integer page;
    private final Integer limit;
    private final Sort sort;
    private final Specialization specialization;
    private final BigDecimal priceStart;
    private final BigDecimal priceEnd;
}