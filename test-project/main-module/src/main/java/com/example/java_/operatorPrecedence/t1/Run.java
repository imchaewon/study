package com.example.java_.operatorPrecedence.t1;

public class Run {
	public static void main(String[] args) {

		String s1 = "str";

		if (! (s1 == "str" + "add")){
			System.out.println("true");
		}else {
			System.out.println("false");
		}

		System.out.println();

		if (true || true && false) {
			System.out.println("true");
		}else {
			System.out.println("false");
		}

		System.out.println();

		int i = 0;
		if (i++ == 1) {
			System.out.println("true");
		}else {
			System.out.println("false");
		}

		System.out.println();

		int i2 = 0;
		if (++i2 + 1 == 2) {
			System.out.println("true");
		}else {
			System.out.println("false");
		}


	}
}

/*
	우선순위 높은순
	1. ++i --i
	2. + - * / %
	3. ==
	4. !
	5. && ||
	6. i++ i--
*/








