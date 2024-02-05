package com.example.java_.z.프로그래머스;

import java.util.stream.IntStream;

//합성수 찾기
//약수의 개수가 세 개 이상인 수를 합성수라고 합니다. 자연수 n이 매개변수로 주어질 때 n이하의 합성수의 개수를 return하도록 solution 함수를 완성해주세요.
public class Solution64 {
	public static void main(String[] args) {
		Solution64 s = new Solution64();
		System.out.println(s.solution(6));
	}

	public int solution(int n) {
		return (int) IntStream.rangeClosed(4,n).filter(i -> IntStream.range(2,i).filter(j -> i % j == 0).count() > 0).count();
	}

}
