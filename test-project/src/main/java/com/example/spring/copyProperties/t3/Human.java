package com.example.spring.copyProperties.t3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class Human extends Wrapper implements Cloneable{
	String name;
	int age;
	float weight;

	public Wrapper clone() throws CloneNotSupportedException {
		return (Wrapper) super.clone();
	}

}
