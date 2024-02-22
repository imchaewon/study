package com.example.java_.class_.t1;

public class Run {
	public static void main(String[] args) {

		int i1 = 12;
		long l1 = 1234;
		float f1 = 3.14f;
		double d1 = 3.141592;
		Integer i2 = 123;
		Long l2 = 1234L;
		Float f2 = 3.14f;
		Double d2 = 3.141592;
		String s1 = "가나";

		System.out.println(i1);
		System.out.println(l1);
		System.out.println(f1);
		System.out.println(d1);

		System.out.println();

		System.out.println(i2.getClass());
		System.out.println(l2.getClass());
		System.out.println(f2.getClass());
		System.out.println(d2.getClass());
		System.out.println(s1.getClass());

		System.out.println();

		System.out.println(i2.getClass().getName());
		System.out.println(l2.getClass().getName());
		System.out.println(f2.getClass().getName());
		System.out.println(d2.getClass().getName());
		System.out.println(s1.getClass().getName());

		System.out.println();

		System.out.println(i2.getClass().getSimpleName());
		System.out.println(l2.getClass().getSimpleName());
		System.out.println(f2.getClass().getSimpleName());
		System.out.println(d2.getClass().getSimpleName());
		System.out.println(s1.getClass().getSimpleName());



	}
}
