package com.tserashkevich.patientservice.repositories;

import com.tserashkevich.patientservice.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID>, QuerydslPredicateExecutor<Patient> {
    Boolean existsByPhoneNumber(String phoneNumber);
}
