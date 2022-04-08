package com.salesianos.triana.dam.EasyCar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class EasyCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyCarApplication.class, args);
	}

}
