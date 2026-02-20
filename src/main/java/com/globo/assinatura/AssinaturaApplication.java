package com.globo.assinatura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AssinaturaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssinaturaApplication.class, args);
	}

}
