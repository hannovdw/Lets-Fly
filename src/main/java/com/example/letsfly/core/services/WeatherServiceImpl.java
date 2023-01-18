package com.example.letsfly.core.services;

import com.example.letsfly.core.services.dto.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class WeatherServiceImpl implements WeatherService {


    private final RestTemplate restTemplate;

    @Autowired
    public WeatherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Weather getWeather() {

        Random randomNum = new Random();
        int randomWindSpeed = randomNum.nextInt(20);

        Weather weather = new Weather();
        weather.setWindSpeed(randomWindSpeed);

        return weather;

    }

}
