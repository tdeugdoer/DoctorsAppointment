package com.tserashkevich.feedbackservice.dtos;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class FeedbackResponse {
    private final UUID id;
    private final UUID appointment;
    private final UUID service;
    private final UUID doctor;
    private final UUID patient;
    private final Integer rating;
    private final String comment;
    private final LocalDateTime creationTime;
}
