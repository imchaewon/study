package com.example.java_._codingTest._programmers;

import java.util.Arrays;

//문자열 계산하기
//my_string은 "3 + 5"처럼 문자열로 된 수식입니다.
//문자열 my_string이 매개변수로 주어질 때, 수식을 계산한 값을 return 하는 solution 함수를 완성해주세요.
public class Solution82 {
	public static void main(String[] args) {
		Solution82 s = new Solution82();
		System.out.println(s.solution("3 + 4"));
		System.out.println(s.solution("3 - 4"));
		System.out.println(s.solution("3 + 4 + 1"));
		System.out.println(s.solution2("3 + 4 + 1"));
	}

	public int solution(String my_string) {
		int answer = 0;

		String[] arr = my_string.split(" ");

		for (int i = 0; i < arr.length; i++) {
			if(i % 2 == 1){
				if(i == 1)
					if(arr[i].equals("+"))
						answer += Integer.parseInt(arr[i - 1]) + Integer.parseInt(arr[i + 1]);
					else
						answer += Integer.parseInt(arr[i - 1]) - Integer.parseInt(arr[i + 1]);
				else
					if(arr[i].equals("+"))
						answer += Integer.parseInt(arr[i + 1]);
					else
						answer -= Integer.parseInt(arr[i + 1]);
			}
		}

		return answer;
	}

	public int solution2(String my_string) {
		return Arrays.stream(my_string.replaceAll("- ", "-").replaceAll("[+] ", "").trim().split(" ")).mapToInt(Integer::parseInt).sum();
	}

}