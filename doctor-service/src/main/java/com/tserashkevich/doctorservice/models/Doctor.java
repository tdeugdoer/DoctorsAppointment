package com.tserashkevich.doctorservice.models;

import com.tserashkevich.doctorservice.models.enums.Gender;
import com.tserashkevich.doctorservice.models.enums.Specialization;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false, length = 15)
    private String name;

    @Column(nullable = false, length = 15)
    private String surname;

    @Column(nullable = false, length = 15)
    private String patronymic;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Specialization specialization;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(unique = true, nullable = false, length = 12)
    private String phoneNumber;

    @Column
    private Integer experience;

    @Column
    private LocalDate birthDate;
}
