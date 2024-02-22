package com.example.java_.z.프로그래머스;

import java.util.Arrays;
import java.util.Stack;

//컨트롤 제트
//숫자와 "Z"가 공백으로 구분되어 담긴 문자열이 주어집니다.
//문자열에 있는 숫자를 차례대로 더하려고 합니다.
//이 때 "Z"가 나오면 바로 전에 더했던 숫자를 뺀다는 뜻입니다.
//숫자와 "Z"로 이루어진 문자열 s가 주어질 때, 머쓱이가 구한 값을 return 하도록 solution 함수를 완성해보세요.
public class Solution87 {
	public static void main(String[] args) {
		Solution87 s = new Solution87();
		System.out.println(s.solution("1 2 Z 3"));
		System.out.println(s.solution2("1 2 Z 3"));
		System.out.println(s.solution3("1 2 Z 3"));
	}

	public int solution(String s) {
		int answer = 0;

		String[] s1 = s.split(" ");
		for (int i = 0; i < s1.length; i++)
			answer += s1[i].equals("Z") ? Integer.parseInt(s1[i - 1]) * -1 : Integer.parseInt(s1[i]);

		return answer;
	}

	public int solution2(String s) {
		Stack<Integer> stack = new Stack<>();

		for (String s1 : s.split(" ")) {
			if(s1.equals("Z"))
				stack.pop();
			else
				stack.push(Integer.parseInt(s1));
		}

		return stack.stream().reduce(Integer::sum).orElse(0);
	}

	public int solution3(String s) {
		return Arrays.stream(s.replaceAll("-?[0-9] Z","").split(" ")).filter(i -> !i.equals("")).mapToInt(Integer::parseInt).sum();
	}

}










