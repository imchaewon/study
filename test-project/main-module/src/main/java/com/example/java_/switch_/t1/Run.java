package com.example.java_.switch_.t1;

public class Run {
	public static void main(String[] args) {

		String s1 = "b";
		String s2 = "b";
		String s3;

		switch (s1+s2) {
			default:
				s3 = "def";
				System.out.println("def");
			case "aa":
				s3 = "aa";
				System.out.println("aaa");
			case "bb":
				s3 = "bb";
				System.out.println("bbb");
			case "cc":
				s3 = "cc";
				System.out.println("ccc");
		}
		System.out.println(s3);

		s3 = (s1+s2) == "aa" ? "aa" :
				(s1+s2) == "bb" ? "bb" :
						(s1+s2) == "cc" ? "cc" :
								"def";

		System.out.println(s3);

		s3 = (s1+s2).equals("aa") ? "aa" :
			(s1+s2).equals("bb") ? "bb" :
			(s1+s2).equals("cc") ? "cc" :
			"def";

		System.out.println(s3);
	}
}
