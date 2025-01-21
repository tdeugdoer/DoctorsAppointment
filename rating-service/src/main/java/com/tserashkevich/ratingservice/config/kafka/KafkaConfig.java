package com.tserashkevich.ratingservice.config.kafka;

import com.tserashkevich.ratingservice.dtos.kafka.ChangeAvgDoctorRatingEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
public class KafkaConfig {

    private final Environment environment;

    public KafkaConfig(Environment environment) {
        this.environment = environment;
    }


    @Bean
    public ProducerFactory<String, ChangeAvgDoctorRatingEvent> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                environment.getProperty("spring.kafka.bootstrap-servers"));
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                environment.getProperty("spring.kafka.producer.key-serializer"));
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                environment.getProperty("spring.kafka.producer.value-serializer"));
        configProps.put(JsonSerializer.TYPE_MAPPINGS,
                environment.getProperty("spring.kafka.producer.properties.spring.json.type.mapping"));
        configProps.put(ProducerConfig.RETRIES_CONFIG,
                environment.getProperty("spring.kafka.producer.retries"));

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, ChangeAvgDoctorRatingEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic changeAvgDoctorRatingProducerTopic() {
        return TopicBuilder
                .name(Objects.requireNonNull(environment.getProperty("spring.kafka.producer.change-avg-doctor-rating-topic.name")))
                .build();
    }
}


