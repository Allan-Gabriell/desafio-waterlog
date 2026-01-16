package com.br.desafio.watter_collector.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenMeteoClientProducer {
    private  final RestTemplate restTemplate = new  RestTemplate();

    @Value("${weather.api.url}")
    private String baseUrl;
    @Value("${weather.latitude}")
    private Double latitude;
    @Value("${weather.longitude}")
    private Double longitude;

    public String fetchWeather() {
        String url = baseUrl +
                "?latitude=" + latitude +
                "&longitude=" + longitude +
                "&current=temperature_2m,wind_speed_10m,weather_code";

        return restTemplate.getForObject(url, String.class);
    }
}
