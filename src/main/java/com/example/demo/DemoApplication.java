package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan; // <-- Le nouvel import pour Spring Boot 4.x
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.mbemnova.epidemie")
@EntityScan(basePackages = "com.mbemnova.epidemie.entity")
@EnableJpaRepositories(basePackages = "com.mbemnova.epidemie.repository")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
