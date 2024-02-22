package com.example.java_.z.프로그래머스;

import java.util.Arrays;

//문자열 my_str과 n이 매개변수로 주어질 때,
//my_str을 길이 n씩 잘라서 저장한 배열을 return하도록 solution 함수를 완성해주세요.
public class Solution78 {
	public static void main(String[] args) {
		Solution78 s = new Solution78();
		System.out.println(Arrays.toString(s.solution("abc1Addfggg4556b22", 6)));
	}

	public String[] solution(String my_str, int n) {
		int size = my_str.length() / n + (my_str.length() % n == 0 ? 0 : 1);
		String[] answer = new String[size];

		for(int i = 0; i < size; i++)
			answer[i] = my_str.substring(i * n, Math.min(my_str.length(), i * n + n));

		return answer;
	}

}
