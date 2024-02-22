package com.example.java_.z.프로그래머스;

import java.util.Arrays;

//자릿수 더하기
//정수 n이 매개변수로 주어질 때 n의 각 자리 숫자의 합을 return하도록 solution 함수를 완성해주세요
public class Solution37 {
	public static void main(String[] args) {
		Solution37 s = new Solution37();
		System.out.println(s.solution(1234));
		System.out.println(s.solution2(1234));
		System.out.println(s.solution3(1234));
	}

	public int solution(int n) {
		int answer = 0;
		String[] arr = String.valueOf(n).split("");
		for (String s : arr)
			answer += Integer.parseInt(s);
		return answer;
	}

	public int solution2(int n) {
		int answer = 0;
		for (int i = n; i > 0; i /= 10)
			answer += i % 10;
		return answer;
	}

	public int solution3(int n) {
		return Arrays.stream(String.valueOf(n).split("")).mapToInt(Integer::parseInt).sum();
	}
}
