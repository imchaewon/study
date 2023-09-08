package com.example.java_.stringUtils.z;

import org.springframework.util.StringUtils;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {

		m1();
//		m2();

	}

	private static void m2() {

		String s1 = "서울시 강서구 공항동 767-21";
		String s2 = "";
		String s3 = " ";
		String s4 = null;

		String[] arr1 = s1.split("");
		String[] arr2 = s2.split(" "); // 없는걸로 자르면 원본이 0번지에 들어감
		String[] arr3 = s3.split(" "); // 같은걸로 자르면 담을게 없어서 방이 안만들어짐
//		String[] arr4 = s4.split(" "); // null은 split가 안됨

		System.out.println(Arrays.toString(arr1));
		System.out.println(arr2[0].equals(""));
//		System.out.println(arr3[0].equals("")); // 같은걸로 자르면 담을게 없어서 방이 안만들어짐
		System.out.println("arr1.length: "+arr1.length);
		System.out.println("arr2.length: "+arr2.length);
//		System.out.println("arr3.length: "+arr3.length);
//		System.out.println(arr4.length);

		System.out.println(StringUtils.hasText(s1));
		System.out.println(StringUtils.hasText(s2));
		System.out.println(StringUtils.hasText(s3));
		System.out.println(!s1.trim().equals(""));
		System.out.println(!s2.trim().equals(""));
		System.out.println(!s3.trim().equals(""));

		System.out.println();
		System.out.println(!StringUtils.hasText(s3) || s3.split(" ").length < 3); // return null;
//		System.out.println(s4 == null || s4.split(" ").length < 3); // return null;

	}

	private static void m1() {
		String s1 = "부산광역시 사하구 감천동 17-46";
		String s2 = "";
		String s3 = "a";

		System.out.printf("'%s'\n", s1.substring(0, s1.lastIndexOf(" ")));
		System.out.printf("'%s'\n", s1.substring(0, s1.lastIndexOf(" ") + 1));

		System.out.println("----------");

		System.out.printf("'%s'\n", s2.lastIndexOf(" "));
//		System.out.printf("'%s'\n", s2.substring(0, s2.lastIndexOf(" "))); // StringIndexOutOfBoundsException

		System.out.println("----------");

		System.out.printf("'%s'\n", s3.substring(0, 0));
		System.out.printf("'%s'\n", s3.substring(0, 1));
//		System.out.printf("'%s'\n", s3.substring(0, 999)); // StringIndexOutOfBoundsException

		System.out.println();

		System.out.println("12345".lastIndexOf("5"));

		System.out.println("서울시 강서구 공항동 767-21".lastIndexOf(" "));

		System.out.println(new String(new char[]{'a', 'b', 'c'}));
		StringBuilder sb = new StringBuilder();
		sb.append("a");
		sb.append("b");
		sb.append("c");
		System.out.println(new String(sb));
	}
}
