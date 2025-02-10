package com.example.java_._codingTest._programmers;

//세균 증식
//어떤 세균은 1시간에 두배만큼 증식한다고 합니다. 처음 세균의 마리수 n과 경과한 시간 t가 매개변수로 주어질 때 t시간 후 세균의 수를 return하도록 solution 함수를 완성해주세요.
public class Solution46 {
	public static void main(String[] args) {
		Solution46 s = new Solution46();
		System.out.println(s.solution(2,10));
		System.out.println(s.solution2(2,10));
	}

	public int solution(int n, int t) {
		for (int i = 0; i < t; i++) {
			n *= 2;
		}
		return n;
	}

	public int solution2(int n, int t) {
		return n << t;
	}

}