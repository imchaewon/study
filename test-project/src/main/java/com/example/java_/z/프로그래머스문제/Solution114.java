package com.example.java_.z.프로그래머스문제;

import java.util.Arrays;

//서울에서 김서방 찾기
//String형 배열 seoul의 element중 "Kim"의 위치 x를 찾아, "김서방은 x에 있다"는 String을 반환하는 함수, solution을 완성하세요.
//seoul에 "Kim"은 오직 한 번만 나타나며 잘못된 값이 입력되는 경우는 없습니다.
public class Solution114 {
	public static void main(String[] args) {
		Solution114 s = new Solution114();
		System.out.println(s.solution(new String[]{"Jane", "Kim"}));
	}

	public String solution(String[] seoul) {
		return String.format("김서방은 %d에 있다", Arrays.asList(seoul).indexOf("Kim"));
	}
}



