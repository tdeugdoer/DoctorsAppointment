package com.tserashkevich.doctorservice.dtos.kafka;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChangeAvgDoctorRatingEvent {
    private UUID doctor;
    private Double avgRating;
}
