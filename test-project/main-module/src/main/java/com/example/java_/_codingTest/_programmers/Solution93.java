package com.example.java_._codingTest._programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//등수 매기기
//영어 점수와 수학 점수의 평균 점수를 기준으로 학생들의 등수를 매기려고 합니다.
//영어 점수와 수학 점수를 담은 2차원 정수 배열 score가 주어질 때,
//영어 점수와 수학 점수의 평균을 기준으로 매긴 등수를 담은 배열을 return하도록 solution 함수를 완성해주세요.
public class Solution93 {
	public static void main(String[] args) {
		Solution93 s = new Solution93();
		System.out.println(Arrays.toString(s.solution(new int[][]{{80, 70}, {90, 55}, {40, 70}, {40, 70}, {10, 10}})));
		System.out.println(Arrays.toString(s.solution2(new int[][]{{80, 70}, {90, 55}, {40, 70}, {40, 70}, {10, 10}})));
		System.out.println(Arrays.toString(s.solution3(new int[][]{{80, 70}, {90, 55}, {40, 70}, {40, 70}, {10, 10}})));
	}

	public int[] solution(int[][] score) {
		int[] answer = new int[score.length];
		double[] arr = Arrays.stream(score).mapToDouble(i -> Arrays.stream(i).average().orElse(0)).toArray();
		int rank = arr.length;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if(i == j) continue;
				if(arr[i] >= arr[j]){
					rank--;
				}
			}
			answer[i] = rank;
			rank = arr.length;
		}
		return answer;
	}

	public int[] solution2(int[][] score) {
		List<Integer> scoreList = new ArrayList<>();
		for(int[] t : score){
			scoreList.add(t[0] + t[1]);
		}
		scoreList.sort(Comparator.reverseOrder());

		int[] answer = new int[score.length];
		for(int i=0; i<score.length; i++){
			answer[i] = scoreList.indexOf(score[i][0] + score[i][1])+1;
		}
		return answer;
	}

	public int[] solution3(int[][] score) {
		return Arrays.stream(score).map(arr -> Arrays.stream(arr).average().orElse(0)).mapToInt(n -> Arrays.stream(score).map(arr -> Arrays.stream(arr).average().orElse(0)).sorted(Comparator.reverseOrder()).collect(Collectors.toList()).indexOf(n) + 1).toArray();
	}
}