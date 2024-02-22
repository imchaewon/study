package com.example.java_.z.프로그래머스;

import java.util.Arrays;

//문자열 내 마음대로 정렬하기
//문자열로 구성된 리스트 strings와, 정수 n이 주어졌을 때, 각 문자열의 인덱스 n번째 글자를 기준으로 오름차순 정렬하려 합니다.
//예를 들어 strings가 ["sun", "bed", "car"]이고 n이 1이면 각 단어의 인덱스 1의 문자 "u", "e", "a"로 strings를 정렬합니다.
public class Solution137 {
	public static void main(String[] args) {
		Solution137 s = new Solution137();
		System.out.println(Arrays.toString(s.solution(new String[]{"sun", "bed", "car"}, 1)));
		System.out.println(Arrays.toString(s.solution(new String[]{"abce", "abcd", "cdx"}, 2)));
	}

	public String[] solution(String[] strings, int n) {
//		return Arrays.stream(strings).sorted().map(s -> s.chars().toArray()).sorted(Comparator.comparingInt(o1 -> o1[n])).map(arr -> Arrays.stream(arr).mapToObj(c->String.valueOf((char)c)).collect(Collectors.joining())).toArray(String[]::new);
//		return Arrays.stream(strings).sorted((o1, o2) -> o1.charAt(n) == o2.charAt(n) ? o1.compareTo(o2) : Integer.compare(o1.charAt(n), o2.charAt(n))).toArray(String[]::new);
		return Arrays.stream(strings).sorted((o1, o2) -> o1.charAt(n) == o2.charAt(n) ? o1.compareTo(o2) : o1.charAt(n) - o2.charAt(n)).toArray(String[]::new);
	}
}

// ["abce","abcd","cdx"] 결과
// ["abcd","abce","cdx"] 기대

