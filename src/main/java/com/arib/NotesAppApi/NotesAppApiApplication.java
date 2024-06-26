package com.arib.NotesAppApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NotesAppApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesAppApiApplication.class, args);
	}

}
