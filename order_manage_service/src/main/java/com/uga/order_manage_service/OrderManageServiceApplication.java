package com.uga.order_manage_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderManageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManageServiceApplication.class, args);
	}

}
