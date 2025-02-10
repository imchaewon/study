package com.example.java_._codingTest.otherTest.코테2;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
	}
	static class Solution {
		public int solution(int[] levels) {
			int answer = 0;

			int sum = 0;
			for (int i = 0; i < levels.length; i++) {
				sum += levels[i];
			}
			int r = (int) Math.round(Arrays.stream(levels).max().getAsInt() - (sum / levels.length / 2.0));
			for (int i = levels.length-1; i >= 0; i--) {
				if(levels[i] > r){
					answer = levels[i];
				}
			}

			return answer;
		}
	}
}