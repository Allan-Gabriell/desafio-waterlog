package com.br.desafio.watter_collector.messaging;

import com.br.desafio.watter_collector.config.RabbitConfig;
import com.br.desafio.watter_collector.dto.WeatherMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class WeatherMessagingProducer {
    private final RabbitTemplate rabbitTemplate;

    public WeatherMessagingProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(WeatherMessage message) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.QUEUE,
                message
        );
    }
}
