package com.example.java_.class_.equals.object;

public class Run {
	public static void main(String[] args) {

		String s1 = "asd";
		String s2 = "a";
		String s3 = "sd";
		String s4 = "asd";
		System.out.println(s1 == s2);
		System.out.println(s1 == s2 + s3);
		System.out.println(s1.equals(s2 + s3));
		System.out.println(s1 == s4);

		System.out.println("-----");

		C1 c1 = new C1("김준수",22);
		C1 c2 = new C1("김준수",22);

		System.out.println(c1 == c2);			//false
		System.out.println(c1.equals(c2));		//false

		System.out.println("-----");

		C2 c3 = new C2("김준수",22);
		C2 c4 = new C2("김준수",23);
		C2 c5 = new C2("김준수",22);

		System.out.println(c3.equals(c4));		//false
		System.out.println(c3.equals(c5));		//true

		System.out.println("-----");

		C3 c6 = new C3("김준수",22);
		C3 c7 = new C3("김원철",22);
		C3 c8 = new C3("김원철",66);
		C3 c9 = new C3("김준수",22);

		System.out.println(c6.equals(c7));	//true
		System.out.println(c6.equals(c8));	//false
		System.out.println(c6.equals(c9));	//true

	}
}






















