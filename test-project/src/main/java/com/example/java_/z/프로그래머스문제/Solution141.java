package com.example.java_.z.프로그래머스문제;

import java.util.Calendar;

//2016년
//2016년 1월 1일은 금요일입니다. 2016년 a월 b일은 무슨 요일일까요? 두 수 a ,b를 입력받아 2016년 a월 b일이 무슨 요일인지 리턴하는 함수, solution을 완성하세요.
//요일의 이름은 일요일부터 토요일까지 각각 SUN,MON,TUE,WED,THU,FRI,SAT
//입니다. 예를 들어 a=5, b=24라면 5월 24일은 화요일이므로 문자열 "TUE"를 반환하세요.
//제한 조건
//2016년은 윤년입니다.
//2016년 a월 b일은 실제로 있는 날입니다. (13월 26일이나 2월 45일같은 날짜는 주어지지 않습니다)
public class Solution141 {
	public static void main(String[] args) {
		Solution141 s = new Solution141();
		System.out.println(s.solution(5, 24));
	}

	public String solution(int a, int b) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, a - 1, b);
		switch (calendar.get(Calendar.DAY_OF_WEEK)){
			case 1: return "SUN";
			case 2: return "MON";
			case 3: return "TUE";
			case 4: return "WED";
			case 5: return "THU";
			case 6: return "FRI";
			case 7: return "SAT";
		}
		return "";
	}
}



