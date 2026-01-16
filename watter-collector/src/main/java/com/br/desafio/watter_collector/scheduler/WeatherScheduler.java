package com.br.desafio.watter_collector.scheduler;

import com.br.desafio.watter_collector.client.OpenMeteoClientProducer;
import com.br.desafio.watter_collector.dto.WeatherMessage;
import com.br.desafio.watter_collector.messaging.WeatherMessagingProducer;
import com.br.desafio.watter_collector.service.OpenMeteoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class WeatherScheduler {
    private final OpenMeteoClientProducer client;
    private final OpenMeteoService parser;
    private final WeatherMessagingProducer producer;

    @Value("${weather.city}")
    private String city;

    public WeatherScheduler(OpenMeteoClientProducer client,
                            OpenMeteoService parser,
                            WeatherMessagingProducer producer) {
        this.client = client;
        this.parser = parser;
        this.producer = producer;
    }

//    @Scheduled(cron = "0 0 * * * *")
    @Scheduled(fixedRate = 60_000) // a cada 1 minuto
    public void collect() {
        try {
            String raw = client.fetchWeather();

            WeatherMessage message = parser.parse(raw, city);

            producer.send(message);

            System.out.println("📤 Enviado: " + message);
        } catch (Exception e) {
            System.err.println("❌ Erro ao coletar ou enviar clima: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
