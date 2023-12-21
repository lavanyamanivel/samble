package com.example.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StudentResultsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentResultsApplication.class, args);
	}@Bean
	public RestTemplate rest() {
return new RestTemplate();
}
}
