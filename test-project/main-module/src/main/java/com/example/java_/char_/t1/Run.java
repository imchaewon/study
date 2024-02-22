package com.example.java_.char_.t1;

public class Run {
	public static void main(String[] args) {
		char c1 = 'a';
		char c2 = 'a';
		System.out.printf("%c\n",c1);
		System.out.printf("%c\n",97);
		System.out.printf("%s\n",c1);
		System.out.printf("%c\n","a".charAt(0));
		System.out.println((char) 97);
		System.out.println(Character.toChars(97));

		System.out.println("--------");

		System.out.println("a".charAt(0));
		System.out.println("a".charAt(0) == 97);
		System.out.println(("a".charAt(0)+"").equals("97"));

		System.out.println("--------");

		System.out.println(c1 + c2);
		System.out.println(String.valueOf(c1 + c2) == "194");
		System.out.println(String.valueOf(c1 + c2).equals("194"));

		System.out.println("--------");

		char c3 = 'a';
		System.out.println((int)c3);
		System.out.println(c3+'0'-'0');

		System.out.println(String.valueOf(c3).equals("a"));
		System.out.println(Character.toString(c3).equals("a"));
		System.out.println((c3+"").equals("a"));

		System.out.println("--------");

		char c4 = '5';
		System.out.println(c4 == 5);
		System.out.println(c4-'0' == 5);
		System.out.println(Character.getNumericValue(c4) == 5);

	}
}
