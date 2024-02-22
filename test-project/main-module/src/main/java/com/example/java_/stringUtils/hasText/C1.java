package com.example.java_.stringUtils.hasText;

import static org.springframework.util.StringUtils.hasText;

public class C1 {
	private static String name;

	public static void main(String[] args) {
		System.out.printf("'%s' → %s%n", name, hasText(name));

		name = "";
		System.out.printf("'%s' → %s%n", name, hasText(name));

		name = "asd";
		System.out.printf("'%s' → %s%n", name, hasText(name));

		name = " ";
		System.out.printf("'%s' → %s%n", name, hasText(name));

		name = "1";
		System.out.printf("'%s' → %s%n", name, hasText(name));

		name = ".";
		System.out.printf("'%s' → %s%n", name, hasText(name));
	}
}
