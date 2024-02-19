package com.example.java_.z.프로그래머스;

//옹알이 (2)
//문제 설명
//머쓱이는 태어난 지 11개월 된 조카를 돌보고 있습니다.
//조카는 아직 "aya", "ye", "woo", "ma" 네 가지 발음과 네 가지 발음을 조합해서 만들 수 있는 발음밖에 하지 못하고 연속해서 같은 발음을 하는 것을 어려워합니다.
//문자열 배열 babbling이 매개변수로 주어질 때, 머쓱이의 조카가 발음할 수 있는 단어의 개수를 return하도록 solution 함수를 완성해주세요.

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Solution162 {
	public static void main(String[] args) {
		Solution162 s = new Solution162();
//		System.out.println(s.solution(new String[]{"aya", "yee", "u", "maa"})); // 1
//		System.out.println(s.solution(new String[]{"ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"})); // 2
//		System.out.println(s.solution(new String[]{"ayaayaa"})); // 0
//		System.out.println(s.solution(new String[]{"yeye"})); // 0
		System.out.println(s.solution2(new String[]{"aya", "yee", "u", "maa"})); // 1
	}

	public int solution(String[] babbling) {
		int answer = 0;

		String[] arr = {"aya", "ye", "woo", "ma"};

		for (String b : babbling) {
			List<String> strings = Arrays.asList(arr);
			Iterator<String> iterator = strings.iterator();
			int tmp = 0;
			String tmpStr = "";
			int cnt = 0;
			while (true) {
				if(iterator.hasNext()) {
					String str = iterator.next();
					int idx = b.indexOf(str, tmp);
					if (idx == tmp) {
						if(tmpStr.equals(str))
							break;
						tmpStr = str;
						tmp = idx + str.length();
						if(tmp == b.length()){
							answer++;
							break;
						}
					}else
						if(cnt > arr.length)
							break;
				}else{
					iterator = strings.iterator();
					cnt++;
				}
			}
		}

		return answer;
	}

	public int solution2(String[] babblings) {
		// "aya", "ye", "woo", "ma" 4가지 발음만 가능
		int answer = 0;
		for(int i = 0; i < babblings.length; i++) {
			if(babblings[i].contains("ayaaya") || babblings[i].contains("yeye") || babblings[i].contains("woowoo") || babblings[i].contains("mama")) {
				continue;
			}

			babblings[i] = babblings[i].replace("aya", " ");
			babblings[i] = babblings[i].replace("ye", " ");
			babblings[i] = babblings[i].replace("woo", " ");
			babblings[i] = babblings[i].replace("ma", " ");
			babblings[i] = babblings[i].replace(" ", "");

			if(babblings[i].length() == 0) answer++;

		}
		return answer;
	}
}


















