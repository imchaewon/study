package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.example.spring.t2.mapper"})
public class SpringbootTestProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTestProjectApplication.class, args);
	}

}
