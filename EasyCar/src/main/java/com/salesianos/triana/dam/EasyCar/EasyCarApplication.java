package com.salesianos.triana.dam.EasyCar;

import com.salesianos.triana.dam.EasyCar.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(StorageProperties.class)
public class EasyCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyCarApplication.class, args);
	}

}
