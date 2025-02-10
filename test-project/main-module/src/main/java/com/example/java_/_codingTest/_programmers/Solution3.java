package com.example.java_._codingTest._programmers;

import java.util.Arrays;

// 뒤집기
public class Solution3 {
	public int[] solution(long n) {
		int len = (int) Math.log10(n) + 1;
		int[] answer = new int[len];
		int i = 0;

		while (n > 0) {
			answer[i] = (int) (n % 10);
			n /= 10;
			i++;
		}

		return answer;
	}

	public static void main(String[] args) {
		Solution3 s = new Solution3();
		System.out.println(Arrays.toString(s.solution(123)));
	}
}