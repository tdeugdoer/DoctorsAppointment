package com.tserashkevich.ratingservice.kafka;

import com.tserashkevich.ratingservice.dtos.kafka.ChangeAvgDoctorRatingEvent;
import com.tserashkevich.ratingservice.utils.LogList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ChangeAvgDoctorRatingProducer {
    private final KafkaTemplate<String, ChangeAvgDoctorRatingEvent> kafkaTemplate;

    @Value("${spring.kafka.producer.change-avg-doctor-rating-topic.name}")
    private String topic;

    @Retryable(retryFor = KafkaException.class, backoff = @Backoff(multiplier = 2))
    public void sendChangeAvgDoctorRatingEvent(ChangeAvgDoctorRatingEvent changeAvgDoctorRatingEvent) {
        log.info(LogList.KAFKA_SEND_MESSAGE, changeAvgDoctorRatingEvent);
        kafkaTemplate.send(topic, changeAvgDoctorRatingEvent);
    }

    @Recover
    private void recoverSendChangeAvgDoctorRatingEvent(KafkaException kafkaException, ChangeAvgDoctorRatingEvent changeAvgDoctorRatingEvent) {
        log.info(LogList.KAFKA_SEND_MESSAGE_FAIL, changeAvgDoctorRatingEvent, kafkaException.getMessage());
    }
}
