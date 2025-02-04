package com.tserashkevich.feedbackservice.controllers;

import com.tserashkevich.feedbackservice.config.swagger.FeedbackApi;
import com.tserashkevich.feedbackservice.dtos.*;
import com.tserashkevich.feedbackservice.service.FeedbackService;
import com.tserashkevich.feedbackservice.utils.FeedbackSortList;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/feedbacks")
public class FeedbackController implements FeedbackApi {
    private final FeedbackService feedbackService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FeedbackResponse createFeedback(@Valid @RequestBody FeedbackRequest feedbackRequest) {
        return feedbackService.create(feedbackRequest);
    }

    @PutMapping("/{feedbackId}")
    public FeedbackResponse updateFeedback(@PathVariable UUID feedbackId, @Valid @RequestBody UpdateFeedbackRequest updateFeedbackRequest) {
        return feedbackService.update(feedbackId, updateFeedbackRequest);
    }

    @DeleteMapping("/{feedbackId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFeedback(@PathVariable UUID feedbackId) {
        feedbackService.delete(feedbackId);
    }

    @GetMapping
    public PageResponse<FeedbackResponse> findAllFeedbacks(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                           @RequestParam(defaultValue = "20") @Min(1) @Max(50) int limit,
                                                           @RequestParam(defaultValue = "ID_ASC") FeedbackSortList sort,
                                                           @RequestParam(required = false) UUID appointmentId,
                                                           @RequestParam(required = false) UUID serviceId,
                                                           @RequestParam(required = false) UUID doctorId,
                                                           @RequestParam(required = false) UUID patientId,
                                                           @RequestParam(required = false) Integer rating,
                                                           @RequestParam(required = false) LocalDateTime dateStart,
                                                           @RequestParam(required = false) LocalDateTime dateEnd) {
        FindAllParams findAllParams = FindAllParams.builder()
                .page(page)
                .limit(limit)
                .sort(sort.getValue())
                .appointment(appointmentId)
                .service(serviceId)
                .doctor(doctorId)
                .patient(patientId)
                .rating(rating)
                .dateStart(dateStart)
                .dateEnd(dateEnd)
                .build();
        return feedbackService.findAll(findAllParams);
    }

    @GetMapping("/{feedbackId}")
    public FeedbackResponse findFeedbackById(@PathVariable UUID feedbackId) {
        return feedbackService.findById(feedbackId);
    }

    @GetMapping("/avg/{doctorId}")
    public Double findDoctorAvgFeedback(@PathVariable UUID doctorId) {
        return feedbackService.findDoctorAvgFeedback(doctorId);
    }

    @GetMapping("/feedbacks/{doctorId}")
    public List<FeedbackResponse> findDoctorFeedbacks(@PathVariable UUID doctorId) {
        return feedbackService.findDoctorFeedbacks(doctorId);
    }
}
