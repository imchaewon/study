package com.example.java_.z.프로그래머스문제;

import java.util.Arrays;

//문자열 정렬하기 (1)
//문자열 my_string이 매개변수로 주어질 때, my_string 안에 있는 숫자만 골라 오름차순 정렬한 리스트를 return 하도록 solution 함수를 작성해보세요.
public class Solution48 {
	public static void main(String[] args) {
		Solution48 s = new Solution48();
		System.out.println(Arrays.toString(s.solution("hi12392")));
	}

	public int[] solution(String my_string) {
		return Arrays.stream(my_string.replaceAll("[a-zA-Z]","").split("")).sorted().mapToInt(Integer::parseInt).toArray();
	}

}
