package com.example.java_._codingTest._programmers;

import java.util.stream.IntStream;

//순서쌍의 개수
//순서쌍이란 두 개의 숫자를 순서를 정하여 짝지어 나타낸 쌍으로 (a, b)로 표기합니다.
//자연수 n이 매개변수로 주어질 때 두 숫자의 곱이 n인 자연수 순서쌍의 개수를 return하도록 solution 함수를 완성해주세요.
public class Solution35 {
	public static void main(String[] args) {
		Solution35 s = new Solution35();
		System.out.println(s.solution(20));
		System.out.println(s.solution2(20));
	}

	public int solution(int n) {
		int answer = 0;
		for (int i = 1; i <= n; i++) {
			if(n % i == 0) answer++;
		}
		return answer;
	}

	public int solution2(int n) {
		return (int) IntStream.rangeClosed(1,n).filter(i -> n % i == 0).count();
	}
}