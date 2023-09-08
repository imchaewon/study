package com.example.java_.ternaryOperator.t1;

public class Run {
	public static void main(String[] args) {

		int i = 0;

		Object o = true ? (i = i + 2) : (i = i - 2);
		System.out.println(o);

		System.out.println(i);

		Object o2 = true ? i++ : i--;
		System.out.println(o2);

		System.out.println(i);

	}
}
