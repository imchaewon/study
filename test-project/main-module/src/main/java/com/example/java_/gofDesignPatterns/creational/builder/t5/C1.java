package com.example.java_.gofDesignPatterns.creational.builder.t5;

import lombok.Builder;

public class C1 {
	String name;
	String addr;
	int age;

	public C1() {
	}

	@Builder
	public C1(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "C1{" +
				"name='" + name + '\'' +
				", addr='" + addr + '\'' +
				", age=" + age +
				'}';
	}
}
