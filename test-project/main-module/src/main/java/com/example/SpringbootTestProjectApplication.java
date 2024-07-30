package com.example;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEncryptableProperties
@SpringBootApplication
public class SpringbootTestProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootTestProjectApplication.class, args);
	}
}