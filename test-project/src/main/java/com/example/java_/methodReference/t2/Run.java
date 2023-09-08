package com.example.java_.methodReference.t2;

public class Run {
	public static void main(String[] args) {

		TestInter t = Class1::m1;
		t.copy();

		TestInter2<Integer> t2 = Class1::m2;
		System.out.println(t2.copy());

		TestInter2<String> t3 = Class1::m3;
		System.out.println(t3.copy());

	}
}
