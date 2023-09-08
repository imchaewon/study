package com.example.spring.t2.qualifier;

import org.springframework.stereotype.Component;

//@Qualifier("kiaa")
@Component
public class Kia extends Car{
	@Override
	public String getCEO() {
		return "송호성";
	}
}
