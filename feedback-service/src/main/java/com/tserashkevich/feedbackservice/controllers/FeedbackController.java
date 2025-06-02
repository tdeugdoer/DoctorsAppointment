package com.tserashkevich.feedbackservice.controllers;

import com.tserashkevich.feedbackservice.config.swagger.FeedbackApi;
import com.tserashkevich.feedbackservice.dtos.FeedbackRequest;
import com.tserashkevich.feedbackservice.dtos.FeedbackResponse;
import com.tserashkevich.feedbackservice.dtos.FindAllParams;
import com.tserashkevich.feedbackservice.dtos.PageResponse;
import com.tserashkevich.feedbackservice.dtos.UpdateFeedbackRequest;
import com.tserashkevich.feedbackservice.service.FeedbackService;
import com.tserashkevich.feedbackservice.utils.FeedbackSortList;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
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
                                                           @RequestParam(defaultValue = "500") int limit,
                                                           @RequestParam(defaultValue = "ID_ASC") FeedbackSortList sort,
                                                           @RequestParam(required = false) String appointmentId,
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
