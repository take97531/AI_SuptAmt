package com.example.suptplcy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class SuptPlcyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuptPlcyApplication.class, args);
	}

}
