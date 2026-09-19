package com.harshvardhan.ludex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Ludex Spring Boot application.
 * This class bootstraps the entire application context.
 */
@SpringBootApplication
public class LudexApplication {

	/**
	 * Main method that launches the Spring Boot application.
	 *
	 * @param args command-line arguments (not used)
	 */
	public static void main(String[] args) {
		SpringApplication.run(LudexApplication.class, args);
	}

}
   