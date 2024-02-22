package com.example.java_.z.프로그래머스;

import java.util.Arrays;

//전화번호 목록
//전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
//전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.
public class Solution108 {
	public static void main(String[] args) {
		Solution108 s = new Solution108();
		System.out.println(s.solution2(new String[]{"119", "97674223", "1195524421"}));
		System.out.println(s.solution2(new String[]{"123","456","789"}));
		System.out.println(s.solution2(new String[]{"12","123","1235","567","88"}));
	}

	public boolean solution(String[] phone_book) {
		return Arrays.stream(phone_book).noneMatch(s -> Arrays.stream(phone_book).anyMatch(s2 -> !s2.equals(s) && s2.startsWith(s)));
	}

	public boolean solution2(String[] phone_book) {
		Arrays.sort(phone_book);
		for (int i = 0; i < phone_book.length; i++) {
			if (i != phone_book.length - 1 && phone_book[i + 1].startsWith(phone_book[i]))
				return false;
			if (i != 0 && phone_book[i - 1].startsWith(phone_book[i]))
				return false;
		}
		return true;
	}
}



