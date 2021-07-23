package com.uga.shipping_detail_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ShippingDetailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShippingDetailServiceApplication.class, args);
	}

}
