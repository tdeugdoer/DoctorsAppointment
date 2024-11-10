package com.tserashkevich.appointmentservice.dtos;

import com.tserashkevich.appointmentservice.models.enums.Status;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class FindAllParams {
    private final Integer page;
    private final Integer limit;
    private final Sort sort;
    private final Status status;
    private final LocalDateTime dateStart;
    private final LocalDateTime dateEnd;
    private final BigDecimal priceStart;
    private final BigDecimal priceEnd;
}