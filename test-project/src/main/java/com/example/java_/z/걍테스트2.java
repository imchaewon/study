package com.example.java_.z;

public class 걍테스트2 {
	public static void main(String[] args) {
		int x = 10;
		int y = 20;

		f1(x,y);

		System.out.println("x = " + x + "y = " + y);
	}

	private static void f1(int a, int b) {
		int tmp = a;
		a = b;
		b = tmp;
	}
}
