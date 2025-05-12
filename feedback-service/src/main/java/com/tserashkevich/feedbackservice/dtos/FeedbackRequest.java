package com.tserashkevich.feedbackservice.dtos;

import com.tserashkevich.feedbackservice.utils.ValidationList;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedbackRequest {
    @NotBlank(message = ValidationList.APPOINTMENT_ID_REQUIRED)
    private final String appointment;

    @NotNull(message = ValidationList.RATING_REQUIRED)
    @Min(value = 0, message = ValidationList.LESS_ONE)
    @Max(value = 5, message = ValidationList.MORE_FIVE)
    private final Integer rating;

    private final String comment;

}
