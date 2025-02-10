package com.example.java_._codingTest._programmers;

import java.util.stream.IntStream;

//약수의 개수와 덧셈
//두 정수 left와 right가 매개변수로 주어집니다. left부터 right까지의 모든 수들 중에서,
//약수의 개수가 짝수인 수는 더하고, 약수의 개수가 홀수인 수는 뺀 수를 return 하도록 solution 함수를 완성해주세요.
public class Solution123 {
	public static void main(String[] args) {
		Solution123 s = new Solution123();
		System.out.println(s.solution(1, 2));
		System.out.println(s.solution(13, 17));
		System.out.println(s.solution(24, 27));
	}

	public int solution(int left, int right) {
		return IntStream.rangeClosed(left, right).map(i -> IntStream.rangeClosed(1, i).filter(j -> i % j == 0).count() % 2 == 0 ? i : -i).sum();
	}
}