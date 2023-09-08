package com.example.java_.z.프로그래머스문제;

import java.util.stream.Collectors;

//시저 암호
//어떤 문장의 각 알파벳을 일정한 거리만큼 밀어서 다른 알파벳으로 바꾸는 암호화 방식을 시저 암호라고 합니다.
//예를 들어 "AB"는 1만큼 밀면 "BC"가 되고, 3만큼 밀면 "DE"가 됩니다.
//"z"는 1만큼 밀면 "a"가 됩니다. 문자열 s와 거리 n을 입력받아 s를 n만큼 민 암호문을 만드는 함수, solution을 완성해 보세요.
public class Solution133 {
	public static void main(String[] args) {
		Solution133 s = new Solution133();
		System.out.println(s.solution("AB", 1));
		System.out.println(s.solution("Z", 1));
		System.out.println(s.solution("z", 1));
		System.out.println(s.solution("a B z", 4));
	}

//	a:97 A:65
//	z:122 Z:90

	public String solution(String s, int n) {
//		System.out.println(Arrays.toString(s.chars().toArray()));
//
//		System.out.println(Arrays.toString(s.chars()
//				.mapToObj(c -> c >= 65 && c <= 90 ?
//						c + n > 90 ? c + n - 26 : c + n :
//						c >= 97 && c <= 122 ? c + n > 122 ? 1 : 2 : 3).toArray()));
//
//		System.out.println(s.chars()
//				.mapToObj(c -> String.valueOf((char)(c >= 65 && c <= 90 ?
//							c + n > 90 ?
//							c + n - 26 : c + n :
//						c >= 97 && c <= 122 ? c + n > 122 ?
//							c + n - 26 : c + n :
//						c))).collect(Collectors.joining()));

		return s.chars().mapToObj(c -> String.valueOf((char)(Character.isUpperCase(c) ? c + n > 90 ? c + n - 26 : c + n : Character.isLowerCase(c) ? c + n > 122 ? c + n - 26 : c + n : c))).collect(Collectors.joining());
	}
}



