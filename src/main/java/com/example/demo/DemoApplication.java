package com.example.demo;

import com.example.demo.models.Activity;
import com.example.demo.models.Camper;
import com.example.demo.models.Signup;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.CamperRepository;
import com.example.demo.repository.SignupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	@Transactional
	CommandLineRunner runner(ActivityRepository activityRepository, CamperRepository camperRepository,
							 SignupRepository signupRepository) {
		return (args) -> {
			Activity archery = activityRepository
					.save(new Activity(1, "Archery", 2, LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>()));
			Activity swimming = activityRepository
					.save(new Activity(2, "Swimming", 3, LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>()));
			Camper caitlin = camperRepository
					.save(new Camper(1, "Caitlin", 8, LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>()));
			Camper lizzie = camperRepository
					.save(new Camper(2, "Lizzie", 9, LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>()));
			Signup archerySignup = signupRepository
					.save(new Signup(1, 9, LocalDateTime.now(), LocalDateTime.now(), archery, caitlin));
			Signup swimmingSignup = signupRepository
					.save(new Signup(2, 10, LocalDateTime.now(), LocalDateTime.now(), swimming, caitlin));
			archery.setSignups(List.of(archerySignup, swimmingSignup));
			caitlin.setSignups(List.of(archerySignup, swimmingSignup));
		};
	}

}

