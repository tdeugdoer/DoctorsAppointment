package com.tserashkevich.ratingservice.service;

import com.tserashkevich.ratingservice.dtos.*;

import java.util.List;
import java.util.UUID;

public interface RatingService {
    RatingResponse create(RatingRequest ratingRequest);

    RatingResponse update(UUID ratingId, UpdateRatingRequest updateRatingRequest);

    void delete(UUID ratingId);

    PageResponse<RatingResponse> findAll(FindAllParams findAllParams);

    RatingResponse findById(UUID ratingId);

    Double findDoctorAvgRating(UUID doctorId);

    List<Feedback> findDoctorFeedbacks(UUID doctorId);
}