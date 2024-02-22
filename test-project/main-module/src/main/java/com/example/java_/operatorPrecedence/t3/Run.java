package com.example.java_.operatorPrecedence.t3;

public class Run {
	public static void main(String[] args) {

		int i0 = 0;
		int j0 = 0;

		if (i0++ == 1 || ++j0 == 1) {
			System.out.printf("true,%d,%d\r",i0,j0);
		}	else {
			System.out.printf("false,%d,%d\r",i0,j0);
		}

		System.out.printf("%d,%d\r",i0,j0);


		System.out.println("-----");


		int i1 = 0;
		int j1 = 0;

			System.out.printf("%s","asd");
		if (i1++ == 1 && ++j1 == 1) {
			System.out.printf("true,%d,%d\r",i1,j1);
		}	else {
			System.out.printf("false,%d,%d\r",i1,j1);
		}

		System.out.printf("%d,%d\r",i1,j1);


		System.out.println("--------------------------------------------------");


		int i2 = 0;
		int j2 = 0;
		if (!(i2++ == 0) && !(++j2 == 1)) {
			System.out.printf("true,%d,%d\r",i2,j2);
		}	else {
			System.out.printf("false,%d,%d\r",i2,j2);
		}

		System.out.printf("%d,%d\r",i2,j2);


		System.out.println("-----");


		int i3 = 0;
		int j3 = 0;
		if (!(i3++ == 0 || ++j3 == 1)) {
			System.out.printf("true,%d,%d\r",i3,j3);
		}	else {
			System.out.printf("false,%d,%d\r",i3,j3);
		}

		System.out.printf("%d,%d\r",i3,j3);


		System.out.println("--------------------------------------------------");


	}
}
