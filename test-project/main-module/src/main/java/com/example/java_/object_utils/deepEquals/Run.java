package com.example.java_.object_utils.deepEquals;

import java.util.Objects;

public class Run {
	public static void main(String[] args) {
		String[][] str2D = {{"aaa", "bbb"}, {"AAA", "BBB"}};
		String[][] str2D2 = {{"aaa", "bbb"}, {"AAA", "BBB"}};

		System.out.println(str2D == str2D2);
		System.out.println(str2D.equals(str2D2));
		System.out.println(Objects.equals(str2D, str2D2));
		System.out.println(Objects.deepEquals(str2D, str2D2));
	}
}
