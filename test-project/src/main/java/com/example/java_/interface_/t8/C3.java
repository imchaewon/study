package com.example.java_.interface_.t8;

public class C3 {
	public static void main(String[] args) {
		C1 c1 = new C1();
		C2 c2 = new C2();
		C3 c3 = new C3();

		c3.c3m1(c1);
		c3.c3m1(c2);

		c3.c3m1(new I1() {
			@Override
			public String m1() {
				return "c3o1";
			}
		});
		c3.c3m1(() -> {
			return "c3o2";
		});

		I1 o3 = new I1() {
			@Override
			public String m1() {
				return "c3o3";
			}
		};
		c3.c3m1(o3);
	}
	void c3m1(I1 i1){
		System.out.println("print: " + i1.m1());
	}
}
