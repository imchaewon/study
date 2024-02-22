package com.example.java_.z.프로그래머스;

import java.util.Arrays;

//짝수 홀수 개수
//문제 설명
//정수가 담긴 리스트 num_list가 주어질 때, num_list의 원소 중 짝수와 홀수의 개수를 담은 배열을 return 하도록 solution 함수를 완성해보세요.
public class Solution27 {
	public static void main(String[] args) {
		Solution27 s = new Solution27();
		System.out.println(Arrays.toString(s.solution(new int[]{1, 2, 3, 4, 5})));
		System.out.println(Arrays.toString(s.solution2(new int[]{1, 2, 3, 4, 5})));
	}

	public int[] solution(int[] num_list) {
		int[] answer = new int[2];
		for (int num : num_list) {
			if(num % 2 == 0)
				answer[0]++;
			else
				answer[1]++;
		}
		return answer;
	}
	public int[] solution2(int[] num_list) {
		int[] answer = new int[2];
		for (int i = 0; i < num_list.length; i++) {
			answer[num_list[i] % 2]++;
		}
		return answer;
	}
}