package com.example.java_.z.프로그래머스;

import java.util.Arrays;
import java.util.Comparator;

//겹치는 선분의 길이
//선분 3개가 평행하게 놓여 있습니다.
//세 선분의 시작과 끝 좌표가 [[start, end], [start, end], [start, end]] 형태로 들어있는 2차원 배열 lines가 매개변수로 주어질 때,
//두 개 이상의 선분이 겹치는 부분의 길이를 return 하도록 solution 함수를 완성해보세요.
//lines가 [[0, 2], [-3, -1], [-2, 1]]일 때 그림으로 나타내면 다음과 같습니다.
//line_2.png
//선분이 두 개 이상 겹친 곳은 [-2, -1], [0, 1]로 길이 2만큼 겹쳐있습니다.
public class Solution103 {
	public static void main(String[] args) {
		Solution103 s = new Solution103();
		System.out.println(s.solution(new int[][]{{0,2},{-3,-1},{-2,1}})); // 2
		System.out.println(s.solution(new int[][]{{0,2},{-3,-1},{-2,-1}})); // 1
		System.out.println(s.solution(new int[][]{{3,4},{6,7},{3,7}})); // 2
		System.out.println(s.solution(new int[][]{{1,3},{3,9},{-1,1}})); // 0
		System.out.println(s.solution(new int[][]{{1,3},{2,4},{3,5}})); // 2
		System.out.println(s.solution(new int[][]{{-3,1},{-2,-1},{0,2}})); // 2
		System.out.println(s.solution(new int[][]{{0,1},{2,4},{3,5}})); // 1
		System.out.println(s.solution(new int[][]{{5,7},{6,8},{8,10}})); // 1
		System.out.println(s.solution(new int[][]{{5,7},{6,8},{9,10}})); // 1
		System.out.println(s.solution(new int[][]{{1,3},{2,5},{4,6}})); // 2
		System.out.println(s.solution(new int[][]{{-3,-2},{-1,0},{1,2}})); // 0
		System.out.println(s.solution(new int[][]{{-1,0},{1,2},{-1,0}})); // 1
		System.out.println(s.solution(new int[][]{{-1,0},{-3,-2},{0,2}})); // 0
		System.out.println(s.solution(new int[][]{{-1,0},{0,1},{-2,0}})); // 1
		System.out.println(s.solution(new int[][]{{1,8},{0,6},{3,4}})); // 5
		System.out.println(s.solution(new int[][]{{5,6},{3,4},{1,2}})); // 0
		System.out.println(s.solution(new int[][]{{5,6},{4,5},{3,4}})); // 0
		System.out.println(s.solution(new int[][]{{5,6},{4,5},{3,5}})); // 1
		System.out.println(s.solution(new int[][]{{5,6},{4,5},{3,6}})); // 2
		System.out.println(s.solution(new int[][]{{5,6},{4,5},{3,7}})); // 2
		System.out.println(s.solution(new int[][]{{-1,0},{3,4},{-2,2}})); // 1
		System.out.println(s.solution(new int[][]{{-1,0},{2,3},{-2,2}})); // 1
		System.out.println(s.solution(new int[][]{{-1,0},{2,3},{-2,4}})); // 2 ★4
		System.out.println(s.solution(new int[][]{{6,7},{8,9},{7,10}})); // 1
		System.out.println(s.solution(new int[][]{{-1,0},{0,2},{-1,4}})); // 3
		System.out.println(s.solution(new int[][]{{5,6},{6,7},{7,8}})); // 0
		System.out.println(s.solution(new int[][]{{0,5},{3,9},{1,10}})); // 8
		System.out.println(s.solution(new int[][]{{-1,1},{1,3},{3,9}})); // 0
		System.out.println(s.solution(new int[][]{{-1,1},{1,3},{4,9}})); // 0
		System.out.println(s.solution(new int[][]{{2,4},{5,7},{3,6}})); // 2
		System.out.println(s.solution(new int[][]{{0,2},{-3,-1},{-2,1}})); // 2
		System.out.println(s.solution(new int[][]{{-3,-1},{-2,3},{2,3}})); // 2
		System.out.println(s.solution(new int[][]{{0,3},{-3,-1},{-2,3}})); // 4
	}

	public int solution(int[][] lines) {
		int[][] ints = Arrays.stream(lines).sorted(Comparator.comparingInt(o -> o[1])).toArray(int[][]::new);
		int[][] ints1 = Arrays.stream(lines).sorted(Comparator.comparingInt(o -> o[0])).toArray(int[][]::new);
		int mid = ints[1][1] - ints1[1][0];
		int[][] arrs = Arrays.stream(lines).flatMap(arr -> Arrays.stream(lines).filter(arr2 -> arr[0] != arr2[0]).filter(arr2 -> arr[0] > arr2[1])).toArray(int[][]::new);
		int cnt = arrs.length;
		int maxA = Arrays.stream(lines).mapToInt(arr -> arr[0]).max().orElse(0);
		int forSubB = arrs.length == 0 ? 0 : arrs[0][1];
		int sub = forSubB != 0 ? maxA - forSubB : 0;
		int resultSub = cnt < 2 ? sub : 0;
		int subC1A = ints1[1][0] > ints[0][1] ? ints1[2][0] - ints1[1][0] : 0;
		int subC1B = ints[1][1] < ints1[2][0] ? ints[1][1] - ints[0][1] : 0;
		int subC2 = ints1[2][0] == ints[1][1] || ints[0][1] == ints1[1][0] ? ints1[2][0] - ints[0][1] : 0;

		return Math.max(mid - resultSub - subC1A - subC1B - subC2, 0);
	}

}


