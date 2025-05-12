package com.tserashkevich.doctorservice.dtos.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
