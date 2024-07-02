package com.example.java_.equalsAndHashCode.t3;

public class Run {
	public static void main(String[] args) {
		Child c1 = new Child("a", 1);
		Child p2 = new Child("a", 1);

		c1.ps1 = "b";
		p2.ps1 = "c";

		System.out.println("c1.equals(c2) = " + c1.equals(p2));
	}
}