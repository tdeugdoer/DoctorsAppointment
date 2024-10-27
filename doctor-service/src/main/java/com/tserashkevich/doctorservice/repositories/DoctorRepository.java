package com.tserashkevich.doctorservice.repositories;

import com.tserashkevich.doctorservice.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID>, QuerydslPredicateExecutor<Doctor> {
    Optional<Doctor> findByPhoneNumber(String phoneNumber);

    @Query("select e from Doctor e where lower(e.name) like lower(concat('%', :search, '%')) " +
            "or lower(e.surname) like lower(concat('%', :search, '%'))" +
            "or lower(e.surname) like lower(concat('%', :search, '%'))" +
            "or lower(e.phoneNumber) like lower(concat('%', :search, '%'))")
    List<Doctor> search(@Param("search") String searchLine);
}
