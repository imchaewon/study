package com.example.java_.class_.innerClass.t1;

public class Run {
	public static void main(String[] args) {
		Run r = new Run();
		r.m1();
		C1.C2.m2();
//		C1.C2.m3();
	}

	void m1() {
		C1.C2 c2 = new C1.C2();
		System.out.println(c2);

		C1.C2.m2();
//		C1.C2.m3();
	}
}
