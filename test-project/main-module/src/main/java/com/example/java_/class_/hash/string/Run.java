package com.example.java_.class_.hash.string;

public class Run {
	public static void main(String[] args) {

		String s1 = "aa";
		String s2 = "bb";
		String s3 = "aa";

		System.out.println(s1 == s2);
		System.out.println(s1 == s3);

		System.out.println("-----");

		String s4 = "ab";
		String s5 = "a";

		System.out.println(s4 == s5 + "b");
		System.out.println(s4.equals(s5 + "b"));

		System.out.println("-----");

		String s6 = new String("ab");
		String s7 = new String("ab");

		System.out.println(s6 == s7);
		System.out.println(s6.equals(s7));

		System.out.println("------");

		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s3.hashCode());
		System.out.println(s4.hashCode());
		System.out.println(s5.hashCode());
		System.out.println(s6.hashCode());
		System.out.println(s7.hashCode());

		System.out.println("-----");

		System.out.println(System.identityHashCode(s1));
		System.out.println(System.identityHashCode(s2));
		System.out.println(System.identityHashCode(s3));
		System.out.println(System.identityHashCode(s4));
		System.out.println(System.identityHashCode(s5));
		System.out.println(System.identityHashCode(s6));
		System.out.println(System.identityHashCode(s7));

		System.out.println("-----");

		String s8 = "aaa";
		System.out.println(s8.hashCode());

		System.out.println("-----");

		String s9 = "Z@S.ME";
		String s10 = "Z@RN.E";
		System.out.println(s9.hashCode());
		System.out.println(s10.hashCode());
		System.out.println("Z@S.ME".hashCode()=="Z@RN.E".hashCode());
		System.out.println(s9 == s10);
		System.out.println(s9.equals(s10));


	}
}
