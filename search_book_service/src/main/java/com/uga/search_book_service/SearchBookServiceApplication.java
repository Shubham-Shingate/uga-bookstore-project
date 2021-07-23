package com.uga.search_book_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SearchBookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchBookServiceApplication.class, args);
	}

}
