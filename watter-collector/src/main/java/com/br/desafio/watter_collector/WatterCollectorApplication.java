package com.br.desafio.watter_collector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WatterCollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatterCollectorApplication.class, args);
	}

}
