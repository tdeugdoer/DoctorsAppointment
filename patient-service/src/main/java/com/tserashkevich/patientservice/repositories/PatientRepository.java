package com.tserashkevich.patientservice.repositories;

import com.tserashkevich.patientservice.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID>, QuerydslPredicateExecutor<Patient> {
    Optional<Patient> findByPhoneNumber(String phoneNumber);

    @Query("select e from Patient e where lower(e.name) like lower(concat('%', :search, '%')) " +
            "or lower(e.surname) like lower(concat('%', :search, '%'))" +
            "or lower(e.surname) like lower(concat('%', :search, '%'))" +
            "or lower(e.phoneNumber) like lower(concat('%', :search, '%'))")
    List<Patient> search(@Param("search") String searchLine);
}
