package com.example.java_._codingTest._programmers;

//문제 설명
//두 정수 X, Y의 임의의 자리에서 공통으로 나타나는 정수 k(0 ≤ k ≤ 9)들을 이용하여 만들 수 있는 가장 큰 정수를 두 수의 짝꿍이라 합니다(단, 공통으로 나타나는 정수 중 서로 짝지을 수 있는 숫자만 사용합니다).
//X, Y의 짝꿍이 존재하지 않으면, 짝꿍은 -1입니다. X, Y의 짝꿍이 0으로만 구성되어 있다면, 짝꿍은 0입니다.
//
//예를 들어, X = 3403이고 Y = 13203이라면, X와 Y의 짝꿍은 X와 Y에서 공통으로 나타나는 3, 0, 3으로 만들 수 있는 가장 큰 정수인 330입니다.
//다른 예시로 X = 5525이고 Y = 1255이면 X와 Y의 짝꿍은 X와 Y에서 공통으로 나타나는 2, 5, 5로 만들 수 있는 가장 큰 정수인 552입니다(X에는 5가 3개, Y에는 5가 2개 나타나므로 남는 5 한 개는 짝 지을 수 없습니다.)
//두 정수 X, Y가 주어졌을 때, X, Y의 짝꿍을 return하는 solution 함수를 완성해주세요.

import java.util.*;
import java.util.stream.Collectors;

public class Solution165 {
	public static void main(String[] args) {
		Solution165 s = new Solution165();
//		System.out.println(s.solution3("100", "2345")); // "-1"
//		System.out.println(s.solution3("100", "203045")); // "0"
//		System.out.println(s.solution3("100", "123450")); // "10"
		System.out.println(s.solution3("12321", "42531")); // "321"
//		System.out.println(s.solution3("5525", "1255")); // "552"
	}

	public String solution(String X, String Y) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < X.length(); i++) {
			String s = String.valueOf(X.charAt(i));
			if(Y.contains(s)) {
				Y = Y.replaceFirst(s, "");
				list.add(Integer.parseInt(s));
			}
		}
		list.sort(Comparator.reverseOrder());
		if(list.isEmpty())
			list.add(-1);
		else if (list.get(0) == 0) {
			list.clear();
			list.add(0);
		}
		return list.stream().map(String::valueOf).collect(Collectors.joining());
	}

	public String solution2(String X, String Y) {
		int[] frequency = new int[10]; // assuming X and Y contain only digits

		for (char c : Y.toCharArray()) {
			if (Character.isDigit(c)) {
				frequency[c - '0']++;
			}
		}

		StringBuilder result = new StringBuilder();

		for (char c : X.toCharArray()) {
			if (Character.isDigit(c) && frequency[c - '0'] > 0) {
				result.append(c);
				frequency[c - '0']--;
			}
		}

		if (result.length() == 0) {
			return "-1";
		} else if (result.charAt(0) == '0') {
			return "0";
		} else {
			char[] sortedChars = result.toString().toCharArray();
			Arrays.sort(sortedChars);
			return new StringBuilder(new String(sortedChars)).reverse().toString();
		}
	}

	public String solution3(String X, String Y) {
		StringBuilder answer = new StringBuilder();
		int[] x = {0,0,0,0,0,0,0,0,0,0};
		int[] y = {0,0,0,0,0,0,0,0,0,0};
		for(int i=0; i<X.length();i++){
			x[X.charAt(i)-48] += 1;
		}
		for(int i=0; i<Y.length();i++){
			y[Y.charAt(i)-48] += 1;
		}

		for(int i=9; i >= 0; i--){
			for(int j=0; j<Math.min(x[i],y[i]); j++){
				answer.append(i);
			}
		}
		if("".equals(answer.toString())){
			return "-1";
		}else if(answer.toString().charAt(0)==48){
			return "0";
		}else {
			return answer.toString();
		}
	}
}