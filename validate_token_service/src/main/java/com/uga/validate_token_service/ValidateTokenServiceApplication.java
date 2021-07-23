package com.uga.validate_token_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ValidateTokenServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValidateTokenServiceApplication.class, args);
	}

}
