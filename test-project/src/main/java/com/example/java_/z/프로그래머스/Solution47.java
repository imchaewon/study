package com.example.java_.z.프로그래머스;

import java.util.Arrays;

//n의 배수 고르기
//정수 n과 정수 배열 numlist가 매개변수로 주어질 때, numlist에서 n의 배수가 아닌 수들을 제거한 배열을 return하도록 solution 함수를 완성해주세요.
public class Solution47 {
	public static void main(String[] args) {
		Solution47 s = new Solution47();
		System.out.println(Arrays.toString(s.solution(3, new int[]{4, 5, 6, 7, 8, 9, 10, 11, 12})));
	}

	public int[] solution(int n, int[] numlist) {
		return Arrays.stream(numlist).filter(i -> i % n == 0).toArray();
	}

}
