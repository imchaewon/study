package com.example.java_._codingTest._programmers;

import java.util.HashMap;

//완주하지 못한 선수
//문제 설명
//수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.
//마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때,
//완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요
public class Solution106 {
	public static void main(String[] args) {
		Solution106 s = new Solution106();
		System.out.println(s.solution(new String[]{"leo", "kiki", "eden"}, new String[]{"eden", "kiki"}));
	}

	public String solution(String[] participant, String[] completion) {
		HashMap<String, Integer> hm = new HashMap<>();
		for (String item:participant)
			hm.put(item, hm.getOrDefault(item, 0) + 1);
		for (String item:completion)
			hm.put(item, hm.get(item) - 1);
		for (String key:hm.keySet())
			if (hm.get(key) == 1)
				return key;
		return null;
	}
}