package com.example.java_.z.프로그래머스;

import java.util.Arrays;
import java.util.Collections;

// 내림차순 정렬
public class Solution4 {
	public long solution(long n) {
		long answer = 0;

		Integer[] arr = new Integer[(int) (Math.log10(n) + 1)];

		int i = 0;
		while (n > 0) {
			arr[i] = (int) (n % 10);
			n /= 10;
			i++;
		}
		Arrays.sort(arr,Collections.reverseOrder());
		String result = "";
		for (int j = 0; j < arr.length; j++) {
			result += arr[j];
		}
		answer = Long.parseLong(result);

		return answer;
	}

	public static void main(String[] args) {
		Solution4 s = new Solution4();
		System.out.println(s.solution(2152169758L));
	}
}
