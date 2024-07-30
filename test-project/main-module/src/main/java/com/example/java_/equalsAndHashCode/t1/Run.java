package com.example.java_.equalsAndHashCode.t1;

public class Run {
	public static void main(String[] args) {
		Child c1 = new Child("a", 1);
		Child c2 = new Child("a", 1);

		System.out.println("c1.equals(c2) = " + c1.equals(c2));
	}
}