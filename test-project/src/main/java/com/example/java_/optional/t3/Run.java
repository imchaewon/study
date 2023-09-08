package com.example.java_.optional.t3;

import java.util.Optional;

public class Run {
	public static void main(String[] args) {

		String s1 = "aa";
		String s2 = "";
		String s3 = null;

		Optional<String> o1 = Optional.ofNullable(s1);
		Optional<String> o2 = Optional.ofNullable(s2);
		Optional<String> o3 = Optional.ofNullable(s3);

		System.out.println(o1);
		System.out.println(o2);
		System.out.println(o3);

		System.out.println("-----");

		String s4 = o1.orElse("값이 없음");
		String s5 = o2.orElse("값이 없음");
		String s6 = o3.orElse("값이 없음");

		System.out.println(s4);
		System.out.println(s5);
		System.out.println(s6);

		System.out.println("-----");

	}
}
