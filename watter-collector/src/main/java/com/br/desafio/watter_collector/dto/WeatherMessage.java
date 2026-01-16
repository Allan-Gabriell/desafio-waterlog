package com.br.desafio.watter_collector.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherMessage {
    private String city;
    private Double temperature;
    private Double windSpeed;
    private Integer weatherCode;
    private LocalDateTime timestamp;
}
