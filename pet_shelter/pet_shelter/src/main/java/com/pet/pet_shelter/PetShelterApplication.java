package com.pet.pet_shelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PetShelterApplication {

	public static void main(String[] args) {
//		SpringApplication.run(PetShelterApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(PetShelterApplication .class);

		builder.headless(false);

		ConfigurableApplicationContext context = builder.run(args);
	}

}
