package com.tweetvibe.service;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class WeatherDataConsumer {

    @KafkaListener(topics = "tweet-vibe", groupId = "group_id")
    public void consumeWeatherData(String weatherDataJson) {
        // In a real application, you would deserialize the JSON data back to WeatherData object
        // For demonstration purposes, we'll directly print the JSON string
        System.out.println("=========");
        System.out.println(weatherDataJson);
    }
}
