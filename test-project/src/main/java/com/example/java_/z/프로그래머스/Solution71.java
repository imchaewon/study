package com.example.java_.z.프로그래머스;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//가까운 수
//정수 배열 array와 정수 n이 매개변수로 주어질 때, array에 들어있는 정수 중 n과 가장 가까운 수를 return 하도록 solution 함수를 완성해주세요.
//가장 가까운 수가 여러 개일 경우 더 작은 수를 return 합니다.
public class Solution71 {
	public static void main(String[] args) {
		Solution71 s = new Solution71();
		System.out.println(s.solution(new int[]{10, -12, 2, 10, 16}, -13));
		System.out.println(s.solution2(new int[]{10, -12, 2, 10, 16}, -13));
	}

	public int solution(int[] array, int n) {
		int answer = 0;
		int l = -1;
		int r = -1;
		List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
		list.add(n);
		Collections.sort(list);
		int idx = list.indexOf(n);
		if (idx != 0)
			l = list.get(idx) - list.get(idx-1);
		if (idx != list.size() - 1)
			r = list.get(idx+1) - list.get(idx);
		if(!(l == -1 || r == -1)){
			if(l > r)
				answer = list.get(idx+1);
			else
				answer = list.get(idx-1);
		}else
			answer = idx == 0 ? list.get(idx + 1) : list.get(idx - 1);
		return answer;
	}

	public int solution2(int[] array, int n) {
		return Math.min(
			array[Arrays.stream(array).map(i -> Math.abs(n - i)).boxed().collect(Collectors.toList()).indexOf(Arrays.stream(array).map(i -> Math.abs(n - i)).min().orElse(0))],
			array[Arrays.stream(array).map(i -> Math.abs(n - i)).boxed().collect(Collectors.toList()).lastIndexOf(Arrays.stream(array).map(i -> Math.abs(n - i)).min().orElse(0))]
		);
	}

}
