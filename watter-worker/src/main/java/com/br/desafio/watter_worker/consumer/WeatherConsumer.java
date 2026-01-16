package com.br.desafio.watter_worker.consumer;

import com.br.desafio.watter_worker.DTO.WeatherMessage;
import com.br.desafio.watter_worker.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class WeatherConsumer {
    private final EmailService emailService;

    public WeatherConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "weather.queue")
    public void receiveMessage(@Payload WeatherMessage message) {
        System.out.println("📥 Recebido do Rabbit:");
        System.out.println(message);
        emailService.sendEmail(message);
    }
}
