package com.tserashkevich.appointmentservice.dtos.doctorWorkDay;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class DoctorWorkDayFindAllParams {
    private final Integer page;
    private final Integer limit;
    private final Sort sort;
    private final UUID doctor;
    private final List<UUID> services;
    private final LocalDate dateStart;
    private final LocalDate dateEnd;

}