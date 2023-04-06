package com.masai;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProspectaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProspectaApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();

	}
	
	@Bean
	public RestTemplate restTemplate() {

		return new RestTemplate();
	}

}
