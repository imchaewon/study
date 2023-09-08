package com.example.java_.z.프로그래머스문제;

import java.util.*;

//최댓값 만들기 (1)
//정수 배열 numbers가 매개변수로 주어집니다. numbers의 원소 중 두 개를 곱해 만들 수 있는 최댓값을 return하도록 solution 함수를 완성해주세요.
public class Solution32 {
	public static void main(String[] args) {
		Solution32 s = new Solution32();
		System.out.println(s.solution(new int[]{0, 31, 24, 10, 1, 9}));
		System.out.println(s.solution2(new int[]{0, 31, 24, 10, 1, 9}));
	}

	public int solution(int[] numbers) {
		int[] arr = Arrays.stream(numbers).sorted().toArray();
		return arr[numbers.length - 1] * arr[numbers.length - 2];
	}
	public int solution2(int[] numbers) {
		Arrays.sort(numbers);
		return numbers[numbers.length - 1] * numbers[numbers.length - 2];
	}
}
