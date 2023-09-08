package com.example.java_.z.프로그래머스문제;

import java.util.LinkedHashSet;
import java.util.Set;

//유한소수 판별하기
//소수점 아래 숫자가 계속되지 않고 유한개인 소수를 유한소수라고 합니다.
//분수를 소수로 고칠 때 유한소수로 나타낼 수 있는 분수인지 판별하려고 합니다. 유한소수가 되기 위한 분수의 조건은 다음과 같습니다.
//기약분수로 나타내었을 때, 분모의 소인수가 2와 5만 존재해야 합니다.
//두 정수 a와 b가 매개변수로 주어질 때, a/b가 유한소수이면 1을, 무한소수라면 2를 return하도록 solution 함수를 완성해주세요.

public class Solution92 {
	public static void main(String[] args) {
		Solution92 s = new Solution92();
//		System.out.println(s.solution(7,20)); // 1
//		System.out.println(s.solution(10,3)); // 2
//		System.out.println(s.solution(11,22)); // 1
//		System.out.println(s.solution(25, 30)); // 2
//		System.out.println(s.solution(1,30)); // 2
//		System.out.println(s.solution(3500, 500)); // 1
		System.out.println(s.solution(12,36)); // 2
	}

	public int solution(int a, int b) {
		int min = Math.min(a,b);
		for (int i = 2; i <= min / 2; i++)
			while(a % i == 0 && b % i == 0) {
				a /= i;
				b /= i;
			}
		if (a % min == 0 && b % min == 0)
			b /= min;
		Set<Integer> factor = factor(b);
		return factor.stream().parallel().anyMatch(n -> !(n == 2 || n == 5)) ? 2 : 1;
	}

	public Set<Integer> factor(int b){
		Set<Integer> set = new LinkedHashSet<>();
		int n = 2;
		while(n <= b)
			if(b % n == 0){
				set.add(n);
				b /= n;
			}else
				n++;
		return set;
	}
}










