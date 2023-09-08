package com.example.java_.z.프로그래머스문제;

import java.util.Arrays;

//배열 회전시키기
//정수가 담긴 배열 numbers와 문자열 direction가 매개변수로 주어집니다.
//배열 numbers의 원소를 direction방향으로 한 칸씩 회전시킨 배열을 return하도록 solution 함수를 완성해주세요.
public class Solution56 {
	public static void main(String[] args) {
		Solution56 s = new Solution56();
		System.out.println(Arrays.toString(s.solution(new int[]{1, 2, 3},"right")));
	}

	public int[] solution(int[] numbers, String direction) {
		int len = numbers.length;
		int[] answer = new int[len];

		for (int i = 0; i < len; i++) {
			if (direction.equals("left")){
				if (i == len - 1){
					answer[i] = numbers[0];
					continue;
				}
				answer[i] = numbers[i + 1];
			}else{
				if (i == 0) {
					answer[0] = numbers[len - 1];
					continue;
				}
				answer[i] = numbers[i - 1];
			}
		}

		return answer;
	}

}
