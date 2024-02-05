package com.example.java_.z.프로그래머스;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//한 번만 등장한 문자
//문자열 s가 매개변수로 주어집니다. s에서 한 번만 등장하는 문자를 사전 순으로 정렬한 문자열을 return 하도록 solution 함수를 완성해보세요. 한 번만 등장하는 문자가 없을 경우 빈 문자열을 return 합니다.
public class Solution73 {
	public static void main(String[] args) {
		Solution73 s = new Solution73();
		System.out.println(s.solution("abcabcaxdck"));
		System.out.println(s.solution2("abcxabcadck"));
	}

	public String solution(String s) {
		return Arrays.stream(s.split("")).filter(i -> Collections.frequency(Arrays.stream(s.split("")).collect(Collectors.toList()), i)==1).distinct().sorted().collect(Collectors.joining());
	}

	public String solution2(String s) {
		Map<String, List<String>> collect = Arrays.stream(s.split("")).collect(Collectors.groupingBy(i -> i));
		return collect.entrySet().stream().filter(i -> i.getValue().size() == 1).map(Map.Entry::getKey).sorted().collect(Collectors.joining());
	}

}
