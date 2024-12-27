package com.example.java_._codingTest.otherTest.코테1;

public class Run {
	public static void main(String[] args) {

		Solution s = new Solution();

		System.out.println(s.solution("12223"));

	}

	static class Solution {
		public int solution(String s) {
			int answer = -1;
			for (int i = 0; i < s.length() - 3; i++) {
				String word = s.substring(i,i+3);
				if(word.substring(0, 1).equals(word.substring(1, 2)) &&
						word.substring(1, 2).equals(word.substring(2, 3))){
					answer = Integer.parseInt(word);
				}
			}
			return answer;
		}
	}
}