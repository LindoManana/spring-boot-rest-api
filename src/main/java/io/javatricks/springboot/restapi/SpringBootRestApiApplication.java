package io.javatricks.springboot.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.javatricks.springboot.restapi.exception.handler.ApiErrorResponse;

@SpringBootApplication
public class SpringBootRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiApplication.class, args);
	}

	@Bean
	public ApiErrorResponse apiErrorResponse() {
		return new ApiErrorResponse();
	}

}
