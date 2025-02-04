package com.tserashkevich.feedbackservice.repositories;

import com.tserashkevich.feedbackservice.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, UUID>, QuerydslPredicateExecutor<Feedback> {
    List<Feedback> findAllByDoctor(UUID doctorId);

    Boolean existsByAppointment(UUID appointmentId);

    @Query("SELECT AVG(r.rating) FROM Feedback r WHERE r.doctor = :doctorId")
    Double findAverageRatingByDoctor(@Param("doctorId") UUID doctorId);
}
