package com.gachon.healthdiary;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class HealthdiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthdiaryApplication.class, args);
	}

	@Bean
	public CommandLineRunner runNextJsApp() {
		return args -> {
			ProcessBuilder processBuilder = new ProcessBuilder("npm","run","dev");
			processBuilder.directory(new File("/Users/penloo/Documents/GitHub/Lecture-Management/src/manageFE"));
			try {
				Process process = processBuilder.start();

				// Optional: Print the output of the external command
				BufferedReader reader =
						new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
	}
}
