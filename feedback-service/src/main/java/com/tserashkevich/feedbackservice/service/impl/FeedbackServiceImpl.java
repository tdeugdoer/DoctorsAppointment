package com.tserashkevich.feedbackservice.service.impl;

import com.querydsl.core.types.Predicate;
import com.tserashkevich.feedbackservice.dtos.*;
import com.tserashkevich.feedbackservice.dtos.feign.AppointmentResponse;
import com.tserashkevich.feedbackservice.dtos.kafka.ChangeAvgDoctorRatingEvent;
import com.tserashkevich.feedbackservice.exceptions.AppointmentNotFoundException;
import com.tserashkevich.feedbackservice.exceptions.FeedbackExistException;
import com.tserashkevich.feedbackservice.exceptions.FeedbackNotFoundException;
import com.tserashkevich.feedbackservice.exceptions.feign.OtherServiceNotFoundException;
import com.tserashkevich.feedbackservice.feign.AppointmentFeignClient;
import com.tserashkevich.feedbackservice.kafka.ChangeAvgDoctorRatingProducer;
import com.tserashkevich.feedbackservice.mappers.FeedbackMapper;
import com.tserashkevich.feedbackservice.models.Feedback;
import com.tserashkevich.feedbackservice.models.QFeedback;
import com.tserashkevich.feedbackservice.repositories.FeedbackRepository;
import com.tserashkevich.feedbackservice.service.FeedbackService;
import com.tserashkevich.feedbackservice.utils.LogList;
import com.tserashkevich.feedbackservice.utils.QPredicates;
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
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;
    private final ChangeAvgDoctorRatingProducer changeAvgDoctorRatingProducer;
    private final AppointmentFeignClient appointmentFeignClient;

    @Override
    public FeedbackResponse create(FeedbackRequest feedbackRequest) {
        Feedback feedback = feedbackMapper.toModel(feedbackRequest);
        checkFeedbackExist(feedback);

        feedbackMapper.updateModel(feedback, getAppointment(feedback.getAppointment()));
        feedback.setCreationTime(LocalDateTime.now());

        feedbackRepository.save(feedback);
        log.info(LogList.CREATE_FEEDBACK, feedback.getId());

        sendChangeAvgDoctorFeedback(feedback.getDoctor());

        return feedbackMapper.toResponse(feedback);
    }

    @Override
    public FeedbackResponse update(UUID feedbackId, UpdateFeedbackRequest updateFeedbackRequest) {
        Feedback feedback = getOrThrow(feedbackId);

        feedbackMapper.updateModel(feedback, updateFeedbackRequest);

        feedbackRepository.save(feedback);
        log.info(LogList.EDIT_FEEDBACK, feedbackId);

        sendChangeAvgDoctorFeedback(feedback.getDoctor());

        return feedbackMapper.toResponse(feedback);
    }

    @Override
    public void delete(UUID feedbackId) {
        Feedback feedback = getOrThrow(feedbackId);

        feedbackRepository.delete(feedback);
        log.info(LogList.DELETE_FEEDBACK, feedbackId);

        sendChangeAvgDoctorFeedback(feedback.getDoctor());
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponse<FeedbackResponse> findAll(FindAllParams findAllParams) {
        Pageable pageable = PageRequest.of(findAllParams.getPage(), findAllParams.getLimit(), findAllParams.getSort());
        Predicate predicate = QPredicates.builder()
                .add(findAllParams.getAppointment(), QFeedback.feedback.appointment::eq)
                .add(findAllParams.getService(), QFeedback.feedback.service::eq)
                .add(findAllParams.getDoctor(), QFeedback.feedback.doctor::eq)
                .add(findAllParams.getPatient(), QFeedback.feedback.patient::eq)
                .add(findAllParams.getRating(), QFeedback.feedback.rating::eq)
                .add(findAllParams.getDateStart(), QFeedback.feedback.creationTime::after)
                .add(findAllParams.getDateEnd(), QFeedback.feedback.creationTime::before)
                .build();

        Page<Feedback> appointmentPage = feedbackRepository.findAll(predicate, pageable);
        List<FeedbackResponse> appointmentResponses = feedbackMapper.toResponses(appointmentPage.getContent());

        log.info(LogList.FIND_ALL_FEEDBACKS);
        return PageResponse.<FeedbackResponse>builder()
                .objectList(appointmentResponses)
                .totalElements(appointmentPage.getTotalElements())
                .totalPages(appointmentPage.getTotalPages())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public FeedbackResponse findById(UUID feedbackId) {
        Feedback feedback = getOrThrow(feedbackId);
        log.info(LogList.FIND_FEEDBACK, feedbackId);
        return feedbackMapper.toResponse(feedback);
    }

    @Override
    public Double findDoctorAvgFeedback(UUID doctorId) {
        Double avgFeedback = feedbackRepository.findAverageRatingByDoctor(doctorId);
        log.info(LogList.COUNT_AVG_FEEDBACK, doctorId);
        return Optional.ofNullable(avgFeedback).orElse(0.0);
    }

    @Override
    public List<FeedbackResponse> findDoctorFeedbacks(UUID doctorId) {
        List<Feedback> feedbacks = feedbackRepository.findAllByDoctor(doctorId);
        log.info(LogList.FIND_FEEDBACKS, doctorId);
        return feedbackMapper.toResponses(feedbacks);
    }

    private Feedback getOrThrow(UUID feedbackId) {
        Optional<Feedback> optionalFeedback = feedbackRepository.findById(feedbackId);
        return optionalFeedback.orElseThrow(FeedbackNotFoundException::new);
    }

    private void checkFeedbackExist(Feedback feedback) {
        if (feedbackRepository.existsByAppointment(feedback.getAppointment()))
            throw new FeedbackExistException();
    }

    private void sendChangeAvgDoctorFeedback(UUID doctorId) {
        changeAvgDoctorRatingProducer.sendChangeAvgDoctorRatingEvent(
                ChangeAvgDoctorRatingEvent.builder()
                        .doctor(doctorId)
                        .avgRating(findDoctorAvgFeedback(doctorId))
                        .build()
        );
    }

    private AppointmentResponse getAppointment(UUID appointmentId) {
        try {
            return appointmentFeignClient.findAppointment(appointmentId);
        } catch (OtherServiceNotFoundException e) {
            throw new AppointmentNotFoundException();
        }
    }
}
