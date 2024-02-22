package com.example.java_.class_.equals.t2;

public class Run {
	public static void main(String[] args) {
		String s1 = "";
		Object o1 = "";

		System.out.println(s1 == o1);
		System.out.println(s1.equals(o1));
		System.out.println(s1.equals(String.valueOf(o1)));
	}
}
