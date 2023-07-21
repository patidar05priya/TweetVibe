package com.tweetvibe.controller;

import com.tweetvibe.service.WeatherDataFetcher;
import com.tweetvibe.service.WeatherProducerService;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherDataController {

    private final WeatherProducerService weatherProducerService;
    private final WeatherDataFetcher weatherDataFetcher;

    public WeatherDataController(WeatherProducerService weatherProducerService, WeatherDataFetcher weatherDataFetcher) {
        this.weatherProducerService = weatherProducerService;
        this.weatherDataFetcher = weatherDataFetcher;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void produce() {
       // weatherProducerService.sendMessageToKafkaTopic("tweet-vibe", "hello");
        weatherDataFetcher.fetchWeatherDataAndSendToKafka();
    }
}
