package com.example.RESTAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {
		Database.getInstance().putCustomer(new Client("Anton","1234",1000, Client.customerType.SINGLE));
		SpringApplication.run(RestApiApplication.class, args);
	}
}
