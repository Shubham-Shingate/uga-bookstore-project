package com.uga.forwords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ForwordsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForwordsApplication.class, args);
	}

}
