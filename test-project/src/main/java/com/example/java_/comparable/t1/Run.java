package com.example.java_.comparable.t1;

public class Run {
	public static void main(String[] args) {

		String s1 = "a";
		String s2 = "z";

		System.out.println(s1.compareTo(s2));
		System.out.println(s2.compareTo(s1));

		s1 = "가도멸괵";
		s2 = "혈관성머리아픔";

		System.out.println(s1.compareTo(s2));
		System.out.println(s2.compareTo(s1));

		System.out.println(s1.compareTo(s2) > 0 ? "s1이 s2보다 더 높음" : "s1이 s2보다 더 낮음");

	}
}
