package com.assignment.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages =  {"com.assignment.search"})
@SpringBootApplication
@EnableFeignClients
public class SearchHandsetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchHandsetApplication.class, args);
	}

}
