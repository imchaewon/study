package com.example.java_.instanceof_.t1;

public class Run {
	public static void main(String[] args) {

		String s1 = "asd";
		String s2 = new String("asd");

		m1(s1);
		m1(s2);

		System.out.println("-----");

		int i1 = 123;
		Integer i2 = 123;

		m1(i1);
		m1(i2);

		System.out.println("-----");

		Object o1 = s1;
		Object o2 = s2;
		Object o3 = i1;
		Object o4 = i2;

		m1(o1);
		m1(o2);
		m1(o3);
		m1(o4);

	}

	public static void m1(Object o){
		System.out.println(o + " is String? " + (o instanceof String));
		System.out.println(o + " is Integer? " + (o instanceof Integer));
	}
}
