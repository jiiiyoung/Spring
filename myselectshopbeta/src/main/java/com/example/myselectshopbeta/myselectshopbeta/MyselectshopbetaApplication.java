package com.example.myselectshopbeta.myselectshopbeta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MyselectshopbetaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyselectshopbetaApplication.class, args);
	}

}
