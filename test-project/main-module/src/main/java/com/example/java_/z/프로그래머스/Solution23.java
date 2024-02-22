package com.example.java_.z.프로그래머스;

import java.util.Arrays;

// 배열 두배 만들기
// 정수 배열 numbers가 매개변수로 주어집니다. numbers의 각 원소에 두배한 원소를 가진 배열을 return하도록 solution 함수를 완성해주세요.
public class Solution23 {
	public static void main(String[] args) {
		Solution23 s = new Solution23();
		System.out.println(Arrays.toString(s.solution(new int[]{1, 2, 3, 4, 5})));
		System.out.println(Arrays.toString(s.solution2(new int[]{1, 2, 3, 4, 5})));
	}
	public int[] solution(int[] numbers) {
		int[] answer = new int[numbers.length];
		for (int i=0; i<numbers.length; i++)
			answer[i] = numbers[i] * 2;
		return answer;
	}
	public int[] solution2(int[] numbers) {
		return Arrays.stream(numbers).map(i -> i * 2).toArray();
	}
}
