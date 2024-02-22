package com.example.java_.method.t2;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {

		int i = 0;
		System.out.println(i);

		m1(i);
		System.out.println(i);

		System.out.println("------------------------------");

		C1 c1 = new C1();
		System.out.println(c1.i1);

		m2(c1);
		System.out.println(c1.i1);

		System.out.println("------------------------------");

		int[] arr = {0};
		System.out.println(Arrays.toString(arr));

		m3(arr);
		System.out.println(Arrays.toString(arr));

	}

	private static void m1(int i) {
		i = 10;
	}

	private static void m2(C1 c1){
		c1.i1 = 10;
	}

	private static void m3(int[] arr) {
		arr[0] = 10;
	}
}

class C1{
	int i1;
}