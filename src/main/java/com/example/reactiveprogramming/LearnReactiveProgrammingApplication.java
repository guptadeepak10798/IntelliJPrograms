package com.example.reactiveprogramming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LearnReactiveProgrammingApplication {


	public static void main(String[] args) {
		SpringApplication.run(LearnReactiveProgrammingApplication.class, args);



	}

	@GetMapping
	public String sayHi(){
		System.out.println("I am executed 123...");
		return "Hello from controller123";
	}

}
