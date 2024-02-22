package com.example.java_.gofDesignPatterns.creational.builder.t1;

public class Run {
	public static void main(String[] args) {
		System.out.println(Obj.builder()
			.age(1)
			.gender("남자")
			.build());
	}
}
