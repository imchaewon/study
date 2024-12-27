package com.example.java_._codingTest._programmers;

import java.util.HashMap;
import java.util.Map;

//로그인 성공?
//머쓱이는 프로그래머스에 로그인하려고 합니다.
//머쓱이가 입력한 아이디와 패스워드가 담긴 배열 id_pw와 회원들의 정보가 담긴 2차원 배열 db가 주어질 때,
//다음과 같이 로그인 성공, 실패에 따른 메시지를 return하도록 solution 함수를 완성해주세요.
public class Solution88 {
	public static void main(String[] args) {
		Solution88 s = new Solution88();
		System.out.println(s.solution(new String[]{"meosseugi", "1234"}, new String[][]{{"rardss", "123"}, {"yyoom", "1234"}, {"meosseugi", "1234"}}));
		System.out.println(s.solution2(new String[]{"meosseugi", "1234"}, new String[][]{{"rardss", "123"}, {"yyoom", "1234"}, {"meosseugi", "1234"}}));
	}

	public String solution(String[] id_pw, String[][] db) {
		String answer = "";

		for (String[] strings : db)
			if(strings[0].equals(id_pw[0])) {
				if (strings[1].equals(id_pw[1]))
					answer = "login";
				else
					answer = "wrong pw";
				break;
			}else
				answer = "fail";

		return answer;
	}

	public String solution2(String[] id_pw, String[][] db) {
		Map<String,String> map = toMap(db);
		return map.containsKey(id_pw[0]) ? map.get(id_pw[0]).equals(id_pw[1]) ? "login" : "wrong pw" : "fail";
	}

	public Map<String,String> toMap(String[][] arr) {
		Map<String,String> map = new HashMap<>();
		for(String[] s : arr)
			map.put(s[0],s[1]);
		return map;
	}

}