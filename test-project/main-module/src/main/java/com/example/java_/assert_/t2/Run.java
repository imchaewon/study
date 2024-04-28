package com.example.java_.assert_.t2;

public class Run {
	public static void main(String[] args) {

		String[] names = {"John", "Mary", "David"};
		assert names.length == 2;
		System.out.println("There are "+names.length + "  names in an array");

	}
}