package com.example.java_.varagrs.t1;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {

		m1();
		m1("a");
		m1("a","b");
		m1(new String[]{"a","b","c"});

	}

	public static void m1(String ... strings) {
		System.out.println(Arrays.toString(strings));
	}
}
