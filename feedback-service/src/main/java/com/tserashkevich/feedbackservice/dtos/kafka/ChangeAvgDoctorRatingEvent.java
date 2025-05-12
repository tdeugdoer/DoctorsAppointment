package com.tserashkevich.feedbackservice.dtos.kafka;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ChangeAvgDoctorRatingEvent {
    private final UUID doctor;
    private final Double avgRating;

}
