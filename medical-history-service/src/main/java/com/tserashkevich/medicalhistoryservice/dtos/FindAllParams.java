package com.tserashkevich.medicalhistoryservice.dtos;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class FindAllParams {
    private final Integer page;
    private final Integer limit;
    private final Sort sort;
    private final UUID patient;
    private final UUID appointment;
    private final UUID doctor;
    private final String diagnosis;
    private final LocalDate dateOfVisitStart;
    private final LocalDate dateOfVisitEnd;

}