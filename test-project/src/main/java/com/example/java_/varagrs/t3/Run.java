package com.example.java_.varagrs.t3;

public class Run {
	public static void main(String[] args) {

		String[] strArr = {"100", "200", "300"};

		System.out.println(concatenate("", "100", "200", "300"));
		System.out.println(concatenate("-", strArr));
		System.out.println(concatenate("-", new String[]{"1", "2", "3"}));
		System.out.printf("\"%s\"\r\n", concatenate("", new String[0]));
		System.out.printf("\"%s\"\r\n", concatenate(""));

	}

	private static String concatenate(String delim, String... args) {
		String result="";

		for (String str : args) {
			result += str + delim;
		}

		return result;
	}

//	private static String concatenate(String... args){
//		return concatenate("-", args);
//	}

}
