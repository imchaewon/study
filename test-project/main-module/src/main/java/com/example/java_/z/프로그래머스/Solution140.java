package com.example.java_.z.프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//두 개 뽑아서 더하기
//정수 배열 numbers가 주어집니다.
//numbers에서 서로 다른 인덱스에 있는 두 개의 수를 뽑아 더해서 만들 수 있는 모든 수를 배열에 오름차순으로 담아 return 하도록 solution 함수를 완성해주세요.
public class Solution140 {
	public static void main(String[] args) {
		Solution140 s = new Solution140();
		System.out.println(Arrays.toString(s.solution(new int[]{2, 1, 3, 4, 1})));
		System.out.println(Arrays.toString(s.solution(new int[]{5, 0, 2, 7})));
	}

	public int[] solution(int[] numbers) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < numbers.length; i++)
			for (int i1 = 0; i1 < i; i1++)
				list.add(numbers[i] + numbers[i1]);
		list.sort(Integer::compareTo);
		return list.stream().distinct().mapToInt(Integer::intValue).toArray();
	}
}



