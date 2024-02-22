package com.example.java_.class_.hash.t1;

public class Run {
	public static void main(String[] args) {

		C1 c1 = new C1("aa");
		C1 c2 = new C1("bb");
		C1 c3 = new C1("aa");

		System.out.println(c1.hashCode());
		System.out.println(c2.hashCode());
		System.out.println(c3.hashCode());

		C2 c4 = new C2("aa");
		C2 c5 = new C2("bb");
		C2 c6 = new C2("aa");

		System.out.println(c4.hashCode());
		System.out.println(c5.hashCode());
		System.out.println(c6.hashCode());

	}
}
