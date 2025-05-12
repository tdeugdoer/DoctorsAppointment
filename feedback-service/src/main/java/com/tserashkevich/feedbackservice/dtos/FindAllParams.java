package com.tserashkevich.feedbackservice.dtos;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class FindAllParams {
    private final Integer page;
    private final Integer limit;
    private final Sort sort;
    private final String appointment;
    private final UUID service;
    private final UUID doctor;
    private final UUID patient;
    private final Integer rating;
    private final LocalDateTime dateStart;
    private final LocalDateTime dateEnd;

}