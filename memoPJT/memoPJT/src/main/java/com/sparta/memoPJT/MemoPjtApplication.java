package com.sparta.memoPJT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MemoPjtApplication {
	public static void main(String[] args) {
		SpringApplication.run(MemoPjtApplication.class, args);
	}
}
