package com.example.java_.method.t4;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class Run {
	public static void main(String[] args) {

		C1 c1 = new C1("asdf");
		System.out.println(c1.s1);

		C1 c2 = copy(c1);
		System.out.println(c2.s1);

	}

	private static C1 copy(C1 c1) {
		C1 c = new C1();
		c.s1 = c1.s1;
		return c;
	}
}

@AllArgsConstructor
@NoArgsConstructor
class C1{
	String s1;
}