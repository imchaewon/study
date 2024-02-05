package com.example.java_.z.프로그래머스;

import java.util.Arrays;

// 배열 자르기
public class Solution6 {
	public static void main(String[] args) {
		Solution6 s = new Solution6();
		System.out.println(Arrays.toString(s.solution(new int[]{1, 2, 3, 4, 5},1,3)));
	}

	public int[] solution(int[] numbers, int num1, int num2) {
		return Arrays.copyOfRange(numbers, num1, num2 + 1);
	}
}
