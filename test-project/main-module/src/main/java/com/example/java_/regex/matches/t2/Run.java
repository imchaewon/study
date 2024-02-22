package com.example.java_.regex.matches.t2;

public class Run {
	public static void main(String[] args) {

		System.out.println("abcd".matches(".*abc.*|.*zxc.*"));
		System.out.println("zzzxcv".matches(".*abc.*|.*zxc.*"));


		System.out.println("-----");

		System.out.println(isTestServer("stage.visitkorea.or.kr"));
		System.out.println(isTestServer("korean.visitkorea.or.kr"));
		System.out.println(isTestServer("dev.ktovisitkorea.com"));
		System.out.println(isTestServer("localhost"));
		System.out.println(isTestServer("127.0.0.1"));

	}

	private static boolean isTestServer(String s) {
		String s2 = "stage.*|korean.*";
		return !s.matches(s2);
	}
}
