package com.example.java_.z.프로그래머스문제;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//연속된 수의 합
//연속된 세 개의 정수를 더해 12가 되는 경우는 3, 4, 5입니다.
//두 정수 num과 total이 주어집니다.
//연속된 수 num개를 더한 값이 total이 될 때, 정수 배열을 오름차순으로 담아 return하도록 solution함수를 완성해보세요.
public class Solution101 {
	public static void main(String[] args) {
		Solution101 s = new Solution101();
//		System.out.println(Arrays.toString(s.solution(3, 12)));
//		System.out.println(Arrays.toString(s.solution(3, 0)));
		System.out.println(Arrays.toString(s.solution(1, 1)));
	}

	public int[] solution(int num, int total) {
		List<Integer> list = new ArrayList<>();
		for (int i = -num; i < total / 2; i++) {
			int sum = 0;
			for (int j = 0; j < num; j++)
				sum += i + j;
			if(sum == total) {
				for (int j = 0; j < num; j++)
					list.add(i + j);
				break;
			}
		}
		return num == 1 ? new int[]{total} : list.stream().mapToInt(Integer::intValue).toArray();
	}

}










