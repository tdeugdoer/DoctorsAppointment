package com.tserashkevich.feedbackservice.mappers;

import com.tserashkevich.feedbackservice.dtos.FeedbackRequest;
import com.tserashkevich.feedbackservice.dtos.FeedbackResponse;
import com.tserashkevich.feedbackservice.dtos.UpdateFeedbackRequest;
import com.tserashkevich.feedbackservice.dtos.feign.AppointmentResponse;
import com.tserashkevich.feedbackservice.models.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FeedbackMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "service", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    Feedback toModel(FeedbackRequest feedbackRequest);

    FeedbackResponse toResponse(Feedback feedback);

    List<FeedbackResponse> toResponses(List<Feedback> feedbacks);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "service", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "appointment", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    void updateModel(@MappingTarget Feedback feedback, UpdateFeedbackRequest updateFeedbackRequest);

    @Mapping(target = "appointment", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "comment", ignore = true)
    @Mapping(target = "creationTime", ignore = true)
    void updateModel(@MappingTarget Feedback feedback, AppointmentResponse appointmentResponse);
}
