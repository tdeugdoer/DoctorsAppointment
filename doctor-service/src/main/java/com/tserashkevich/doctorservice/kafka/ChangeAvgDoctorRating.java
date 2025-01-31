package com.tserashkevich.doctorservice.kafka;

import com.tserashkevich.doctorservice.dtos.kafka.ChangeAvgDoctorRatingEvent;
import com.tserashkevich.doctorservice.services.DoctorService;
import com.tserashkevich.doctorservice.utils.LogList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ChangeAvgDoctorRating {
    private final DoctorService doctorService;

    @KafkaListener(topics = "${spring.kafka.consumer.change-avg-doctor-rating-topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void handle(ChangeAvgDoctorRatingEvent changeAvgDoctorRatingEvent) {
        log.info(LogList.RECEIVED_EVENT, changeAvgDoctorRatingEvent);
        doctorService.changeAvgRating(changeAvgDoctorRatingEvent);
    }
}
