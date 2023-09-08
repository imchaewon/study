package com.example.java_.stringTokenizer.t1;

import java.util.Arrays;
import java.util.StringTokenizer;

public class Run {
	public static void main(String[] args) {
		String s = "100,,,200,300";

		String[] split = s.split(",");
		StringTokenizer tokenizer = new StringTokenizer(s, ",");

		System.out.println(Arrays.toString(split)); // 빈 문자열도 토큰으로 인식

		while(tokenizer.hasMoreTokens())
			System.out.println(tokenizer.nextToken()); // 빈 문자열을 토큰으로 인식하지 않음
	}
}
