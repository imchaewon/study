package com.example.java_.class_.inheritance.t3;

public class C1 {
	String name = "가가";
	int age;

	public C1(){
		System.out.println("부모생성자1");
	}

	public C1(String name, int age) {
		System.out.println("부모생성자2");
		this.name = name;
		this.age = age;
	}
}
