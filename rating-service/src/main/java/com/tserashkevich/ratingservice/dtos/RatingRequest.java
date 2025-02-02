package com.tserashkevich.ratingservice.dtos;

import com.tserashkevich.ratingservice.utils.PatternList;
import com.tserashkevich.ratingservice.utils.ValidationList;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RatingRequest {
    @NotBlank(message = ValidationList.APPOINTMENT_ID_REQUIRED)
    @Pattern(regexp = PatternList.UUID_PATTERN, message = ValidationList.WRONG_UUID_FORMAT)
    private final String appointment;

    @NotNull(message = ValidationList.RATING_REQUIRED)
    @Min(value = 0, message = ValidationList.LESS_ONE)
    @Max(value = 5, message = ValidationList.MORE_FIVE)
    private final Integer rating;

    private final String comment;
}
