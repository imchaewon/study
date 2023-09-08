package com.example.java_.stringUtils.compareTo.t1;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {
		String str = "asdf";

		// 비교대상의 문자열이 포함되어있을경우
		System.out.println(str.compareTo("asdf")); // 완전히 같은 경우 0
		System.out.println(str.compareTo("a")); // 첫번쨰부터 일치하는경우 문자열 길이(length)의 차를 반환 → 4-1=3
		System.out.println(str.compareTo("as")); // 첫번쨰부터 일치하는경우 문자열 길이(length)의 차를 반환 → 4-2=2
		System.out.println(str.compareTo("d")); // 첫번째값이 일치하지않는경우 첫번째값의 아스키값의 차를 반환 → 97-100=-3

		// 비교대상과 다른 문자열인 경우
		System.out.println(str.compareTo("zzz")); // 96-123=-26
		System.out.println(str.compareTo("zxcv")); // 97-122=-25
		System.out.println(str.compareTo("ABCD")); // 97-65=32
	}
}
