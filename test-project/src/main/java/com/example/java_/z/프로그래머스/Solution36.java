package com.example.java_.z.프로그래머스;

import java.util.Arrays;
import java.util.stream.IntStream;

//짝수는 싫어요
//정수 n이 매개변수로 주어질 때, n 이하의 홀수가 오름차순으로 담긴 배열을 return하도록 solution 함수를 완성해주세요.
public class Solution36 {
	public static void main(String[] args) {
		Solution36 s = new Solution36();
		System.out.println(Arrays.toString(s.solution(10)));
	}

	public int[] solution(int n) {
		return IntStream.rangeClosed(0,n).filter(i -> i % 2 == 1).toArray();
	}
}
