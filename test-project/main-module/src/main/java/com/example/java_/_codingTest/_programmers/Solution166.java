package com.example.java_._codingTest._programmers;

//대충 만든 자판
//휴대폰의 자판은 컴퓨터 키보드 자판과는 다르게 하나의 키에 여러 개의 문자가 할당될 수 있습니다. 키 하나에 여러 문자가 할당된 경우, 동일한 키를 연속해서 빠르게 누르면 할당된 순서대로 문자가 바뀝니다.
//
//예를 들어, 1번 키에 "A", "B", "C" 순서대로 문자가 할당되어 있다면 1번 키를 한 번 누르면 "A", 두 번 누르면 "B", 세 번 누르면 "C"가 되는 식입니다.
//
//같은 규칙을 적용해 아무렇게나 만든 휴대폰 자판이 있습니다. 이 휴대폰 자판은 키의 개수가 1개부터 최대 100개까지 있을 수 있으며, 특정 키를 눌렀을 때 입력되는 문자들도 무작위로 배열되어 있습니다.
//또, 같은 문자가 자판 전체에 여러 번 할당된 경우도 있고, 키 하나에 같은 문자가 여러 번 할당된 경우도 있습니다. 심지어 아예 할당되지 않은 경우도 있습니다. 따라서 몇몇 문자열은 작성할 수 없을 수도 있습니다.
//
//이 휴대폰 자판을 이용해 특정 문자열을 작성할 때, 키를 최소 몇 번 눌러야 그 문자열을 작성할 수 있는지 알아보고자 합니다.
//
//1번 키부터 차례대로 할당된 문자들이 순서대로 담긴 문자열배열 keymap과 입력하려는 문자열들이 담긴 문자열 배열 targets가 주어질 때,
//각 문자열을 작성하기 위해 키를 최소 몇 번씩 눌러야 하는지 순서대로 배열에 담아 return 하는 solution 함수를 완성해 주세요.
//
//단, 목표 문자열을 작성할 수 없을 때는 -1을 저장합니다.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Solution166 {
	public static void main(String[] args) {
		Solution166 s = new Solution166();
		System.out.println(s.solution3("100", "2345")); // "-1"
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