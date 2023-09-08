package com.example.java_.z.프로그래머스문제;

import java.util.Arrays;

//다항식 더하기
//한 개 이상의 항의 합으로 이루어진 식을 다항식이라고 합니다. 다항식을 계산할 때는 동류항끼리 계산해 정리합니다.
//덧셈으로 이루어진 다항식 polynomial이 매개변수로 주어질 때,
//동류항끼리 더한 결괏값을 문자열로 return 하도록 solution 함수를 완성해보세요. 같은 식이라면 가장 짧은 수식을 return 합니다.

public class Solution97 {
	public static void main(String[] args) {
		Solution97 s = new Solution97();
		System.out.println(s.solution("3x + 7 + x"));
		System.out.println(s.solution("x"));
		System.out.println(s.solution("12"));
	}

	public String solution(String polynomial) {
		int x = Arrays.stream(polynomial.split(" \\+ ")).filter(s -> s.contains("x")).mapToInt(s -> s.equals("x") ? 1 : Integer.parseInt(s.replace("x",""))).reduce(Integer::sum).orElse(0);
		int n = Arrays.stream(polynomial.split(" \\+ ")).filter(s -> !s.contains("x")).mapToInt(Integer::parseInt).reduce(Integer::sum).orElse(0);
		return (x == 1 ? "x" : x == 0 ? "" : x + "x") + (n == 0 ? "" : x != 0 ? " + " + n : n);
	}

}










