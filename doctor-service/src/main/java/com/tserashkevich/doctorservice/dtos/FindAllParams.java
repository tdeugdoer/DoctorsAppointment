package com.tserashkevich.doctorservice.dtos;

import com.tserashkevich.doctorservice.models.enums.Gender;
import com.tserashkevich.doctorservice.models.enums.Specialization;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;

@Getter
@Builder
public class FindAllParams {
    private final Integer page;
    private final Integer limit;
    private final Sort sort;
    private final Specialization specialization;
    private final Gender gender;
    private final Integer experienceStart;
    private final Integer experienceEnd;
    private final LocalDate birthDateStart;
    private final LocalDate birthDateEnd;
}