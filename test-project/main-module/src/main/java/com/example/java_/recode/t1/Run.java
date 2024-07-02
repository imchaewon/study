package com.example.java_.recode.t1;

public class Run {
	public static void main(String[] args) {
		C1 c1 = new C1("aa", 12);
		String name = c1.name();
		Integer age = c1.age();
		System.out.println("c1 = " + c1);
		System.out.println("name = " + name);
		System.out.println("age = " + age);
	}
}