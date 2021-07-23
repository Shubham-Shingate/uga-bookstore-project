package com.uga.promotion_manage_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PromotionManageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PromotionManageServiceApplication.class, args);
	}

}
