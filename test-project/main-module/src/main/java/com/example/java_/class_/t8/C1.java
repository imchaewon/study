package com.example.java_.class_.t8;

import lombok.ToString;

@ToString
public class C1 extends C2{

	private String name;
	private int age;

	public C1(String name, int age){
		this.name = name;
		this.age = age;
	};

	public void m1(){
		System.out.println("m1");
	}

	public C1 m2(){
		System.out.println("m2");
		return this;
	}

	public C1 m3(){
		System.out.println("m3");
		return this;
	}
}
