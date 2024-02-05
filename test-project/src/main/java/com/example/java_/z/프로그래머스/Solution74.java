package com.example.java_.z.프로그래머스;

import java.util.*;
import java.util.stream.Collectors;

//진료 순서 정하기
//외과의사 머쓱이는 응급실에 온 환자의 응급도를 기준으로 진료 순서를 정하려고 합니다.
//정수 배열 emergency가 매개변수로 주어질 때 응급도가 높은 순서대로 진료 순서를 정한 배열을 return하도록 solution 함수를 완성해주세요.
public class Solution74 {
	public static void main(String[] args) {
		Solution74 s = new Solution74();
		System.out.println(Arrays.toString(s.solution(new int[]{3, 76, 24})));
		System.out.println(Arrays.toString(s.solution2(new int[]{3, 76, 24})));
	}

	public int[] solution(int[] emergency) {
		int[] answer = new int[emergency.length];
		List<Integer> list = Arrays.stream(emergency).boxed().collect(Collectors.toList());
		List<Integer> list2 = new ArrayList<>(list);
		list2.sort(Comparator.reverseOrder());
		for (int i = 0; i < list.size(); i++)
			answer[i] = list2.indexOf(list.get(i)) + 1;
		return answer;
	}

	public int[] solution2(int[] emergency) {
		return Arrays.stream(emergency).map(i -> Arrays.stream(emergency).boxed().sorted(Collections.reverseOrder()).collect(Collectors.toList()).indexOf(i) + 1).toArray();
	}

}
