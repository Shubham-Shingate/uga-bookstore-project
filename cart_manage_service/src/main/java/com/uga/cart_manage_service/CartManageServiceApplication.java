package com.uga.cart_manage_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CartManageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartManageServiceApplication.class, args);
	}

}
