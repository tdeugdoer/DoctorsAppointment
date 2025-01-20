package com.tserashkevich.ratingservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ratings")
public class Rating {
    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private UUID appointment;

    @Column(nullable = false)
    private UUID service;

    @Column(nullable = false)
    private UUID doctor;

    @Column(nullable = false)
    private UUID patient;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    private LocalDateTime creationTime;

    private String comment;
}
