package com.br.desafio.watter_worker.service;

import com.br.desafio.watter_worker.DTO.WeatherMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(WeatherMessage message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("allangabrieldev@gmail.com");
        email.setTo("allangabriel2415@gmail.com");
        email.setSubject("🌦️ Alerta Climático - " + message.getCity());
        email.setText("""
                Clima atualizado:

                Cidade: %s
                Temperatura: %.1f °C
                Vento: %.1f km/h
                Código do clima: %d
                Data/Hora: %s
                """.formatted(
                message.getCity(),
                message.getTemperature(),
                message.getWindSpeed(),
                message.getWeatherCode(),
                message.getTimestamp()
        ));

        mailSender.send(email);
        System.out.println("Email enviado com sucesso!");
    }
}
