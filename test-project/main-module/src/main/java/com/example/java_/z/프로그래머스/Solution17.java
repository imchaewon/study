package com.example.java_.z.프로그래머스;

import java.util.Arrays;
import java.util.OptionalDouble;

// 배열의 평균값
// 정수 배열 numbers가 매개변수로 주어집니다. numbers의 원소의 평균값을 return하도록 solution 함수를 완성해주세요.
public class Solution17 {
	public static void main(String[] args) {
		Solution17 s = new Solution17();
		System.out.println(s.solution(new int[]{1,2,3,4}));
		System.out.println(s.solution2(new int[]{1,2,3,4}));
		System.out.println(s.solution3(new int[]{1,2,3,4}));
	}
	public double solution(int[] numbers) {
		int sum = 0;
		for (int num : numbers){
			sum += num;
		}
		return (double)sum / numbers.length;
	}
	public double solution2(int[] numbers) {
		return Arrays.stream(numbers).average().orElse(0);
	}
	public double solution3(int[] arr) {
		double answer = 0;
		OptionalDouble o = Arrays.stream(arr).average();
		if(o.isPresent())
			answer = o.getAsDouble();
		return answer;
	}
}
