package com.tweetvibe.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WeatherProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public WeatherProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageToKafkaTopic(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    public void sendMessageToKafkaTopic(String message) {
        kafkaTemplate.send("tweet-vibe", message);
    }
}
