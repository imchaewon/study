package com.example.java_.stringUtils.toChar;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {

		String s1 = "asd";

		System.out.println(Arrays.toString(s1.chars().toArray()));
		System.out.println(Arrays.toString(s1.toCharArray()));

	}
}
