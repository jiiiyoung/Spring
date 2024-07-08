package com.sparta.momoPJT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MomoPjtApplication {
	public static void main(String[] args) {
		SpringApplication.run(MomoPjtApplication.class, args);
	}
}
