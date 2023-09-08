package com.example.java_.z.프로그래머스문제;

import java.util.Arrays;
import java.util.stream.IntStream;

//약수 구하기
//정수 n이 매개변수로 주어질 때, n의 약수를 오름차순으로 담은 배열을 return하도록 solution 함수를 완성해주세요.
public class Solution54 {
	public static void main(String[] args) {
		Solution54 s = new Solution54();
		System.out.println(Arrays.toString(s.solution(24)));
	}

	public int[] solution(int n) {
		return IntStream.rangeClosed(1,n).filter(i -> n % i == 0).toArray();
	}

}
