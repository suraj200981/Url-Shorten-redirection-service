package com.example.url.shortner.microservices.redirectionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RedirectionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedirectionServiceApplication.class, args);
	}

}
