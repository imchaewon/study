package com.example.java_.recursive.factorial;

public class Run {
	public static void main(String[] args) {

		System.out.println(m1(5));

	}

	private static int m1(int i) {
		if(i == 0) return 1;
		return i * m1(i - 1);
	}

}
