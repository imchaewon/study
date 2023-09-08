package com.example.java_.recursive.t1;

public class Run {
	public static void main(String[] args) {

		m1(10);

	}

	private static void m1(int i) {
		if(i == 0) return;
		System.out.println(i);
		m1(--i);
	}

}
