package com.example.java_.gofDesignPatterns.creational.factoryMethod.t1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Obj {
	private int age;
	private String gender;

	public static Obj of(int age, String gender) {
		Obj o = new Obj();
			o.setAge(age);
			o.setGender(gender);
		return o;
	}
}
