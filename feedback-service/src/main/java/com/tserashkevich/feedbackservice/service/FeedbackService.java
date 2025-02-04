package com.tserashkevich.feedbackservice.service;

import com.tserashkevich.feedbackservice.dtos.*;

import java.util.List;
import java.util.UUID;

public interface FeedbackService {
    FeedbackResponse create(FeedbackRequest feedbackRequest);

    FeedbackResponse update(UUID feedbackId, UpdateFeedbackRequest updateFeedbackRequest);

    void delete(UUID feedbackId);

    PageResponse<FeedbackResponse> findAll(FindAllParams findAllParams);

    FeedbackResponse findById(UUID feedbackId);

    Double findDoctorAvgFeedback(UUID doctorId);

    List<FeedbackResponse> findDoctorFeedbacks(UUID doctorId);
}