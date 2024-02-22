package com.example.java_.gofDesignPatterns.creational.factoryMethod.t2;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class Obj {
	private int age;
	private String gender;

	public static Obj of(int age, String gender) {
		return Obj.builder()
			.age(age)
			.gender(gender)
			.build();
	}
}
