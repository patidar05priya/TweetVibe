package com.tweetvibe.service;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WeatherDataFetcher {
    OpenWeatherMapClient openWeatherClient;

    private final WeatherProducerService weatherProducerService;

    @Autowired
    public WeatherDataFetcher(WeatherProducerService weatherProducerService) {
        this.weatherProducerService = weatherProducerService;
        this.openWeatherClient = new OpenWeatherMapClient("c4dc9fd5c6793c22ab6f5115c3aa8b36");
        openWeatherClient.setReadTimeout(1000);
    }

    @Scheduled(fixedRate = 3000)
    public void fetchWeatherDataAndSendToKafka() {
        try {
            final String weatherJson = openWeatherClient
                    .currentWeather()
                    .single()
                    .byCityName("Raleigh")
                    .language(Language.ENGLISH)
                    .unitSystem(UnitSystem.METRIC)
                    .retrieve()
                    .asJSON();
            //System.out.println(weatherJson);
            weatherProducerService.sendMessageToKafkaTopic(weatherJson);
            Thread.sleep(4000);
        } catch (Exception e) {
            // Handle exceptions (e.g., API call failure) gracefully
            e.printStackTrace();
        }
    }
}
