package com.example.java_.optional.t1;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Obj1 {
	private String name;
	private int age;
	private Gender gender;

	public Obj1(String name, Gender gender) {
		this.name = name;
		this.gender = gender;
	}

	public Obj1(String name, int age) {
		this.name = name;
		this.age = age;
	}
}
