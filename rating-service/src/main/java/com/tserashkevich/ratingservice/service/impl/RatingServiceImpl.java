package com.tserashkevich.ratingservice.service.impl;

import com.querydsl.core.types.Predicate;
import com.tserashkevich.ratingservice.dtos.*;
import com.tserashkevich.ratingservice.dtos.kafka.ChangeAvgDoctorRatingEvent;
import com.tserashkevich.ratingservice.exceptions.RatingExistException;
import com.tserashkevich.ratingservice.exceptions.RatingNotFoundException;
import com.tserashkevich.ratingservice.kafka.ChangeAvgDoctorRatingProducer;
import com.tserashkevich.ratingservice.mappers.RatingMapper;
import com.tserashkevich.ratingservice.models.QRating;
import com.tserashkevich.ratingservice.models.Rating;
import com.tserashkevich.ratingservice.repositories.RatingRepository;
import com.tserashkevich.ratingservice.service.RatingService;
import com.tserashkevich.ratingservice.utils.LogList;
import com.tserashkevich.ratingservice.utils.QPredicates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final ChangeAvgDoctorRatingProducer changeAvgDoctorRatingProducer;

    @Override
    public RatingResponse create(RatingRequest ratingRequest) {
        Rating rating = ratingMapper.toModel(ratingRequest);
        checkRatingExist(rating);

        rating.setCreationTime(LocalDateTime.now());

        ratingRepository.save(rating);
        log.info(LogList.CREATE_RATING, rating.getId());

        sendChangeAvgDoctorRating(rating.getDoctor());

        return ratingMapper.toRatingResponse(rating);
    }

    @Override
    public RatingResponse update(UUID ratingId, UpdateRatingRequest updateRatingRequest) {
        Rating rating = getOrThrow(ratingId);

        ratingMapper.updateModel(rating, updateRatingRequest);

        ratingRepository.save(rating);
        log.info(LogList.EDIT_RATING, ratingId);

        sendChangeAvgDoctorRating(rating.getDoctor());

        return ratingMapper.toRatingResponse(rating);
    }

    @Override
    public void delete(UUID ratingId) {
        Rating rating = getOrThrow(ratingId);

        ratingRepository.delete(rating);
        log.info(LogList.DELETE_RATING, ratingId);

        sendChangeAvgDoctorRating(rating.getDoctor());
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<RatingResponse> findAll(FindAllParams findAllParams) {
        Pageable pageable = PageRequest.of(findAllParams.getPage(), findAllParams.getLimit(), findAllParams.getSort());
        Predicate predicate = QPredicates.builder()
                .add(findAllParams.getAppointment(), QRating.rating1.appointment::eq)
                .add(findAllParams.getService(), QRating.rating1.service::eq)
                .add(findAllParams.getDoctor(), QRating.rating1.doctor::eq)
                .add(findAllParams.getPatient(), QRating.rating1.patient::eq)
                .add(findAllParams.getRating(), QRating.rating1.rating::eq)
                .add(findAllParams.getDateStart(), QRating.rating1.creationTime::after)
                .add(findAllParams.getDateEnd(), QRating.rating1.creationTime::before)
                .build();

        Page<Rating> appointmentPage = ratingRepository.findAll(predicate, pageable);
        List<RatingResponse> appointmentResponses = ratingMapper.toRatingResponses(appointmentPage.getContent());

        log.info(LogList.FIND_ALL_RATINGS);
        return PageResponse.<RatingResponse>builder()
                .objectList(appointmentResponses)
                .totalElements(appointmentPage.getTotalElements())
                .totalPages(appointmentPage.getTotalPages())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public RatingResponse findById(UUID ratingId) {
        Rating rating = getOrThrow(ratingId);
        log.info(LogList.FIND_RATING, ratingId);
        return ratingMapper.toRatingResponse(rating);
    }

    @Override
    public Double findDoctorAvgRating(UUID doctorId) {
        Double avgRating = ratingRepository.findAverageRatingByDoctor(doctorId);
        log.info(LogList.COUNT_AVG_RATING, doctorId);
        return Optional.ofNullable(avgRating).orElse(0.0);
    }

    @Override
    public List<Feedback> findDoctorFeedbacks(UUID doctorId) {
        List<Rating> ratings = ratingRepository.findAllByDoctor(doctorId);
        log.info(LogList.FIND_FEEDBACKS, doctorId);
        return ratingMapper.toFeedbacks(ratings);
    }

    private Rating getOrThrow(UUID ratingId) {
        Optional<Rating> optionalRating = ratingRepository.findById(ratingId);
        return optionalRating.orElseThrow(RatingNotFoundException::new);
    }

    private void checkRatingExist(Rating rating) {
        if (ratingRepository.existsByAppointment(rating.getAppointment()))
            throw new RatingExistException();
    }

    private void sendChangeAvgDoctorRating(UUID doctorId) {
        changeAvgDoctorRatingProducer.sendChangeAvgDoctorRatingEvent(
                ChangeAvgDoctorRatingEvent.builder()
                        .doctor(doctorId)
                        .avgRating(findDoctorAvgRating(doctorId))
                        .build()
        );
    }
}
