package com.example.spring.t2.qualifier;

import org.springframework.stereotype.Component;

@Component
public class Hyundai extends Car{
	@Override
	public String getCEO() {
		return "장재훈";
	}
}
