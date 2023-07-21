package com.tweetvibe.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TweetVibeProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public TweetVibeProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageToKafkaTopic(String topic, String message) {
        System.out.println("=====");
        kafkaTemplate.send(topic, message);
    }
}
