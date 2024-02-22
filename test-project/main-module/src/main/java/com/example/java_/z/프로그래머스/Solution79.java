package com.example.java_.z.프로그래머스;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//영어가 싫어요
//영어가 싫은 머쓱이는 영어로 표기되어있는 숫자를 수로 바꾸려고 합니다.
//문자열 numbers가 매개변수로 주어질 때, numbers를 정수로 바꿔 return 하도록 solution 함수를 완성해 주세요.
public class Solution79 {
	public static void main(String[] args) {
		Solution79 s = new Solution79();
		System.out.println(s.solution("onetwothreefourfivesixseveneightnine"));
	}

	public long solution(String numbers) {
		Map<String,Integer> map = new HashMap<String,Integer>(){
			{
				put("zero", 0);
				put("one", 1);
				put("two", 2);
				put("three", 3);
				put("four", 4);
				put("five", 5);
				put("six", 6);
				put("seven", 7);
				put("eight", 8);
				put("nine", 9);
			}
		};
		Set<Map.Entry<String, Integer>> set = map.entrySet();
		for(Map.Entry<String, Integer> i : set)
			numbers = numbers.replace(i.getKey(), String.valueOf(i.getValue()));
		return Long.parseLong(String.valueOf(numbers));
	}

}
