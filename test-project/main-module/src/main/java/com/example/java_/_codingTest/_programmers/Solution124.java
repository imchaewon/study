package com.example.java_._codingTest._programmers;

//문자열 다루기 기본
//문자열 s의 길이가 4 혹은 6이고, 숫자로만 구성돼있는지 확인해주는 함수, solution을 완성하세요.
//예를 들어 s가 "a234"이면 False를 리턴하고 "1234"라면 True를 리턴하면 됩니다.
public class Solution124 {
	public static void main(String[] args) {
		Solution124 s = new Solution124();
		System.out.println(s.solution("a234"));
	}

	public boolean solution(String s) {
		try {
			if((s.length() == 4 || s.length() == 6) && Integer.parseInt(s) >= 0) return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}