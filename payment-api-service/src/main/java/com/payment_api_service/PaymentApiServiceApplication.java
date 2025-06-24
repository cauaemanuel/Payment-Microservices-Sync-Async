package com.payment_api_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class PaymentApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApiServiceApplication.class, args);
	}

}
