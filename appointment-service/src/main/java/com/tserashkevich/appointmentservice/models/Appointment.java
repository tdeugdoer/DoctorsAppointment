package com.tserashkevich.appointmentservice.models;

import com.tserashkevich.appointmentservice.models.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointments")
public class Appointment {
    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private UUID service;

    @Column(nullable = false)
    private UUID doctor;

    private UUID patient;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Status status;

    private LocalDateTime date;

    private BigDecimal price;
}
