package com.tserashkevich.ratingservice.repositories;

import com.tserashkevich.ratingservice.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID>, QuerydslPredicateExecutor<Rating> {
    List<Rating> findAllByDoctor(UUID doctorId);

    Boolean existsByAppointment(UUID appointmentId);

    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.doctor = :doctorId")
    Double findAverageRatingByDoctor(@Param("doctorId") UUID doctorId);
}
