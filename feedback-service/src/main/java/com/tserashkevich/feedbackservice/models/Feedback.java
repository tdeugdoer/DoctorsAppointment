package com.tserashkevich.feedbackservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "feedbacks")
public class Feedback {
    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private String appointment;

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
