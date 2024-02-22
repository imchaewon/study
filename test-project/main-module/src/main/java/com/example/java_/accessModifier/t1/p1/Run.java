package com.example.java_.accessModifier.t1.p1;

import com.example.java_.accessModifier.t1.p2.C2;
import com.example.java_.accessModifier.t1.p2.C3;

public class Run {
	public static void main(String[] args) {

		C1 c1 = new C1();
		String s = c1.name;
		double d = c1.score;
//		int i = c1.age;

		C2 c2 = new C2();
		String s2 = c2.name;
//		double d2 = c2.score;
//		int i2 = c2.age;

		C3 c3 = new C3();

		String s3 = c3.name;
		double d3 = c3.score;
//		int i3 = c3.age;

	}
}
