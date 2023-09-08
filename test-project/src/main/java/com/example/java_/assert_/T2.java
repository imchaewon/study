package com.example.java_.assert_;

public class T2 {
	public static void main(String[] args) {

		String[] names = {"John", "Mary", "David"};
		assert names.length == 2;
		System.out.println("There are "+names.length + "  names in an array");

	}
}
