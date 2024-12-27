package com.example.java_._codingTest._programmers;

//숨어있는 숫자의 덧셈 (1)
//문자열 my_string이 매개변수로 주어집니다. my_string안의 모든 자연수들의 합을 return하도록 solution 함수를 완성해주세요.
public class Solution44 {
	public static void main(String[] args) {
		Solution44 s = new Solution44();
		System.out.println(s.solution("aAb1B2cC34oOp"));
		System.out.println(s.solution2("aAb1B2cC34oOp"));
	}

	public int solution(String my_string) {
		int answer = 0;

		char[] str = my_string.toCharArray();
		for(char c : str){
			if(c >= 48 && c <= 57)
				answer += Integer.parseInt(String.valueOf(c));
		}

		return answer;
	}

	public int solution2(String my_string) {
		return my_string.chars().mapToObj(i -> (char) i).filter(Character::isDigit).map(String::valueOf).mapToInt(Integer::valueOf).sum();
	}

}