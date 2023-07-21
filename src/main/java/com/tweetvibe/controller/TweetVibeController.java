package com.tweetvibe.controller;

import com.tweetvibe.service.TweetVibeProducerService;
import org.springframework.web.bind.annotation.*;

@RestController
public class TweetVibeController {

    private final TweetVibeProducerService tweetVibeProducerService;

    public TweetVibeController(TweetVibeProducerService tweetVibeProducerService) {
        this.tweetVibeProducerService = tweetVibeProducerService;

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void produce() {
        tweetVibeProducerService.sendMessageToKafkaTopic("tweet-vibe", "hello");
    }
}
