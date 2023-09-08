package com.example.java_.class_.inheritance.t3;

public class C2 extends C1{
	String name = "나나";
	int age;
	String name2 = super.name;

	public C2(){
		super();
		System.out.println("자식생성자1");
	}

	public C2(String name, int age) {
//		super();
		super("다다",12);
		System.out.println("자식생성자2");
		this.name = name;
		this.age = age;
	}

	public void m1(){
		System.out.println("C2의 m1");
	}
}
