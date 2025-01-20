package com.tserashkevich.appointmentservice.repositories;

import com.tserashkevich.appointmentservice.models.Appointment;
import com.tserashkevich.appointmentservice.models.enums.Status;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID>, QuerydslPredicateExecutor<Appointment> {
    List<Appointment> findByStatusAndDoctor(Status status, UUID doctor, Sort sort);

    @Query("select e from Appointment e where lower(CAST(e.doctor AS STRING)) like lower(concat('%', :search, '%')) " +
            "or lower(CAST(e.patient AS STRING)) like lower(concat('%', :search, '%'))" +
            "or lower(e.status) like lower(concat('%', :search, '%'))")
    List<Appointment> search(@Param("search") String searchLine);
}