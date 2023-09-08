package com.example.java_.class_.arr.t1;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {

		C1[] c1 = new C1[0];
		C1[] c2 = new C1[3];

//		c1[0] = new C1("aa");
		c2[0] = new C1("bb");
		c2[1] = new C1("cc");
		c2[2] = new C1("dd");

		System.out.println(c1);
		System.out.println(c2);
		System.out.println(Arrays.toString(c2));
		System.out.println(c1.length);
		System.out.println(c2.length);
		System.out.println(c2[0]);

		System.out.println("-----");

		C1[] c3 = {new C1("n1"), new C1("n2"), new C1("n3")};

		System.out.println(Arrays.toString(c3));


	}
}
