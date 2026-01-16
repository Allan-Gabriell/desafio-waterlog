package com.br.desafio.watter_worker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherMessage {
    private String city;
    private Double temperature;
    private Double windSpeed;
    private Integer weatherCode;
    private LocalDateTime timestamp;
}
