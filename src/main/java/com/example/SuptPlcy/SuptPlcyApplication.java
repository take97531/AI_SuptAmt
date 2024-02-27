package com.example.SuptPlcy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "supt.amt")
public class SuptPlcyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuptPlcyApplication.class, args);
	}

}
