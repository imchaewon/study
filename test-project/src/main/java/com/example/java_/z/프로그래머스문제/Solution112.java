package com.example.java_.z.프로그래머스문제;

import java.util.stream.LongStream;

//두 정수 사이의 합
//두 정수 a, b가 주어졌을 때 a와 b 사이에 속한 모든 정수의 합을 리턴하는 함수, solution을 완성하세요.
//예를 들어 a = 3, b = 5인 경우, 3 + 4 + 5 = 12이므로 12를 리턴합니다.
public class Solution112 {
	public static void main(String[] args) {
		Solution112 s = new Solution112();
		System.out.println(s.solution(3, 5));
		System.out.println(s.solution(3, 3));
		System.out.println(s.solution(5, 3));
		System.out.println(s.solution(-2, 2));
	}

	public long solution(int a, int b) {
		return LongStream.rangeClosed(Math.min(a,b), Math.max(a,b)).sum();
	}
}



