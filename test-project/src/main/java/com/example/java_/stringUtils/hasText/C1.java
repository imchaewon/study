package com.example.java_.stringUtils.hasText;

import static org.springframework.util.StringUtils.hasText;

public class C1 {
	private String name;

	public void m1(){
		System.out.println(hasText(name));
	}

	public static void main(String[] args) {
		C1 c1 = new C1();
		c1.m1();

		C1 c2 = new C1();
		c2.name = "asd";
		c2.m1();

		C1 c3 = new C1();
		c3.name = " ";
		c3.m1();
	}
}
