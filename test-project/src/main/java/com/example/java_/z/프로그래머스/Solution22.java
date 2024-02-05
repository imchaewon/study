package com.example.java_.z.프로그래머스;

import java.util.*;
import java.util.stream.Collectors;

// 배열 뒤집기
// 정수가 들어 있는 배열 num_list가 매개변수로 주어집니다. num_list의 원소의 순서를 거꾸로 뒤집은 배열을 return하도록 solution 함수를 완성해주세요.
public class Solution22 {
	public static void main(String[] args) {
		Solution22 s = new Solution22();
		System.out.println(Arrays.toString(s.solution(new int[]{1, 0, 1, 1, 1, 3, 5})));
		System.out.println(Arrays.toString(s.solution2(new int[]{1, 0, 1, 1, 1, 3, 5})));
	}
	public int[] solution(int[] num_list) {
		int[] answer = new int[num_list.length];
		for (int i=0; i<num_list.length; i++)
			answer[num_list.length - i - 1] = num_list[i];
		return answer;
	}
	public int[] solution2(int[] num_list) {
		List<Integer> list = Arrays.stream(num_list).boxed().collect(Collectors.toList());
		Collections.reverse(list);
		return list.stream().mapToInt(Integer::intValue).toArray();
	}
}
