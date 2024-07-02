package com.example.java_.equalsAndHashCode.t2;

public class Run {
	public static void main(String[] args) {
		Child c1 = new Child("a", 1);
		Child c2 = new Child("a", 1);

		c1.ps1 = "b";

		System.out.println("c1.equals(c2) = " + c1.equals(c2));
	}
}