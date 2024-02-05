package com.example.java_.z.프로그래머스;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//이진수 더하기
//이진수를 의미하는 두 개의 문자열 bin1과 bin2가 매개변수로 주어질 때, 두 이진수의 합을 return하도록 solution 함수를 완성해주세요.
public class Solution75 {
	public static void main(String[] args) {
		Solution75 s = new Solution75();
//		System.out.println(s.solution("10","11"));
		System.out.println(s.solution2("10","11"));
//		System.out.println(s.solution("0","0"));
	}

	public String solution(String bin1, String bin2) {
		List<String> list = Arrays.stream(bin1.split("")).collect(Collectors.toList());
		List<String> list2 = Arrays.stream(bin2.split("")).collect(Collectors.toList());
		int[] result = new int[Math.max(bin1.length(), bin2.length()) + 1];
		Collections.reverse(list);
		Collections.reverse(list2);
		Arrays.fill(result,-1);
		int maxSize = Math.max(list.size(),list2.size());
		int carry = 0;
		Arrays.fill(result,-1);
		for (int i = 0; i <= maxSize; i++) {
			int a = 0;
			int b = 0;
			if (i < list.size())
				a = Integer.parseInt(list.get(i));
			if (i < list2.size())
				b = Integer.parseInt(list2.get(i));

			result[i] = (a + b + carry) % 2;

			if((a + b + carry) / 2 == 1)
				carry = 1;
			else
				carry = 0;
		}

		List<Integer> list3 = Arrays.stream(result).boxed().collect(Collectors.toList());
		Collections.reverse(list3);
		String answer = list3.stream().map(String::valueOf).collect(Collectors.joining());
		answer = answer.charAt(0) == 48 ? answer.substring(1) : answer;
		return answer;
	}

	public String solution2(String bin1, String bin2) {
		System.out.println(Integer.toString(10,16));
		return Integer.toBinaryString(Integer.parseInt(bin1,2) + Integer.parseInt(bin2,2));
	}

}
