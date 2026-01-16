package com.br.desafio.watter_worker;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class WatterWorkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatterWorkerApplication.class, args);
	}

}
