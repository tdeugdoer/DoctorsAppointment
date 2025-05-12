package com.tserashkevich.patientservice.models;

import com.tserashkevich.patientservice.models.enums.Gender;
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
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
public class Patient {
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
    private Gender gender;

    @Column(unique = true, nullable = false, length = 12)
    private String phoneNumber;

    private LocalDate birthDate;

    @Column(unique = true, length = 50)
    private String image;

}
