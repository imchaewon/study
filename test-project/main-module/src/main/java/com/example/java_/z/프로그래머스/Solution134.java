package com.example.java_.z.프로그래머스;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

//최소직사각형
//명함 지갑을 만드는 회사에서 지갑의 크기를 정하려고 합니다.
//다양한 모양과 크기의 명함들을 모두 수납할 수 있으면서, 작아서 들고 다니기 편한 지갑을 만들어야 합니다.
//이러한 요건을 만족하는 지갑을 만들기 위해 디자인팀은 모든 명함의 가로 길이와 세로 길이를 조사했습니다.
//아래 표는 4가지 명함의 가로 길이와 세로 길이를 나타냅니다.
public class Solution134 {
	public static void main(String[] args) {
		Solution134 s = new Solution134();
		System.out.println(s.solution2(new int[][]{{60, 50}, {30, 70}, {60, 30}, {80, 40}}));
//		System.out.println(s.solution2(new int[][]{{10, 7},{12, 3},{8, 15},{14, 7},{5, 15}}));
//		System.out.println(s.solution2(new int[][]{{14, 4},{19, 6},{6, 16},{18, 7},{7, 11}}));
	}

	public int solution(int[][] sizes) {
		int[][] arr = Arrays.stream(sizes).map(a -> Arrays.stream(a).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray()).sorted(Comparator.comparingInt(o1 -> o1[0])).toArray(int[][]::new);
		return arr[sizes.length - 1][0] * Arrays.stream(arr).flatMapToInt(a -> IntStream.of(a[1])).max().orElse(0);
	}

	public int solution2(int[][] sizes) {
		System.out.println(Arrays.toString(Arrays.stream(sizes).reduce((a, b) -> new int[]{
				Math.max(Math.max(a[0], a[1]), Math.max(b[0], b[1])),
				Math.max(Math.min(a[0], a[1]), Math.min(b[0], b[1]))
		}).orElse(new int[]{})));

		return (Arrays.stream(sizes).reduce((a, b) -> new int[]{
				Math.max(Math.max(a[0], a[1]), Math.max(b[0], b[1])),
				Math.max(Math.min(a[0], a[1]), Math.min(b[0], b[1]))
		}).map(a -> a[0] * a[1]).get());
	}
}