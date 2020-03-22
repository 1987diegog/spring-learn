package com.demente.ideas.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LearnSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(LearnSpringApplication.class, args);
	}
}
