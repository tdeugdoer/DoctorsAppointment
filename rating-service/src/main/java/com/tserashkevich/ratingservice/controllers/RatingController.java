package com.tserashkevich.ratingservice.controllers;

import com.tserashkevich.ratingservice.config.swagger.RatingApi;
import com.tserashkevich.ratingservice.dtos.*;
import com.tserashkevich.ratingservice.service.RatingService;
import com.tserashkevich.ratingservice.utils.RatingSortList;
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
@RequestMapping("/api/v1/ratings")
public class RatingController implements RatingApi {
    private final RatingService ratingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RatingResponse createRating(@Valid @RequestBody RatingRequest ratingRequest) {
        return ratingService.create(ratingRequest);
    }

    @PutMapping("/{ratingId}")
    public RatingResponse updateRating(@PathVariable UUID ratingId, @Valid @RequestBody UpdateRatingRequest updateRatingRequest) {
        return ratingService.update(ratingId, updateRatingRequest);
    }

    @DeleteMapping("/{ratingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRating(@PathVariable UUID ratingId) {
        ratingService.delete(ratingId);
    }

    @GetMapping
    public PageResponse<RatingResponse> findAllRatings(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                       @RequestParam(defaultValue = "20") @Min(1) @Max(50) int limit,
                                                       @RequestParam(defaultValue = "ID_ASC") RatingSortList sort,
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
        return ratingService.findAll(findAllParams);
    }

    @GetMapping("/{ratingId}")
    public RatingResponse findRatingById(@PathVariable UUID ratingId) {
        return ratingService.findById(ratingId);
    }

    @GetMapping("/avg/{doctorId}")
    public Double findDoctorAvgRating(@PathVariable UUID doctorId) {
        return ratingService.findDoctorAvgRating(doctorId);
    }

    @GetMapping("/feedbacks/{doctorId}")
    public List<Feedback> findDoctorFeedbacks(@PathVariable UUID doctorId) {
        return ratingService.findDoctorFeedbacks(doctorId);
    }
}
