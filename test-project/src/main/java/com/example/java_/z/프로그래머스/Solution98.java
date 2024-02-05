package com.example.java_.z.프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//OX퀴즈
//덧셈, 뺄셈 수식들이 'X [연산자] Y = Z' 형태로 들어있는 문자열 배열 quiz가 매개변수로 주어집니다.
//수식이 옳다면 "O"를 틀리다면 "X"를 순서대로 담은 배열을 return하도록 solution 함수를 완성해주세요.
public class Solution98 {
	public static void main(String[] args) {
		Solution98 s = new Solution98();
//		System.out.println(Arrays.toString(s.solution(new String[]{"3 - 4 = -3", "5 + 6 = 11"})));
		System.out.println(Arrays.toString(s.solution(new String[]{"-1 - -3 = 2", "5 + 6 = 11"})));
	}

	public String[] solution(String[] quiz) {
		List<String> list = new ArrayList<>();
		for (String s : quiz) {
			String l = s.split(" = ")[0];
			String r = s.split(" = ")[1];
			String[] arr = l.split(" ");
			int calc = 0;
			for (int i = 0; i < arr.length; i++)
				if ((i + 1) % 2 == 0)
					calc += Integer.parseInt(arr[i - 1]) + Integer.parseInt(arr[i] + 1) * Integer.parseInt(arr[i + 1]);
			list.add(calc == Integer.parseInt(r) ? "O" : "X");
		}
		return list.toArray(String[]::new);
	}

}










