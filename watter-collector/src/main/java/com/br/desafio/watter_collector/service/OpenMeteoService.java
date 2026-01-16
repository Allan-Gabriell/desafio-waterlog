package com.br.desafio.watter_collector.service;

import com.br.desafio.watter_collector.dto.WeatherMessage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OpenMeteoService {

    private final ObjectMapper mapper = new ObjectMapper();

    public WeatherMessage parse(String json, String city) {
        try {

            JsonNode root = mapper.readTree(json);
            JsonNode current = root.path("current");

            double temperature = current.path("temperature_2m").asDouble();
            double windSpeed   = current.path("wind_speed_10m").asDouble();
            int weatherCode    = current.path("weather_code").asInt();

            LocalDateTime timestamp =
                    LocalDateTime.parse(current.path("time").asText());

            return new WeatherMessage(
                    city,
                    temperature,
                    windSpeed,
                    weatherCode,
                    timestamp
            );

        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer parse do clima", e);
        }
    }
}
