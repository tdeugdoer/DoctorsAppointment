package com.tserashkevich.doctorservice.models;

import com.tserashkevich.doctorservice.models.enums.Gender;
import com.tserashkevich.doctorservice.models.enums.Specialization;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "doctors")
public class Doctor {
    @Id
    @UuidGenerator
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

    @Column(unique = true, length = 50)
    private String image;

    @Column
    private Double rating;

}
