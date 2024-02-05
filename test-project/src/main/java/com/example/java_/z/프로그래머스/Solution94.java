package com.example.java_.z.프로그래머스;

import java.util.Arrays;

//특이한 정렬
//정수 n을 기준으로 n과 가까운 수부터 정렬하려고 합니다.
//이때 n으로부터의 거리가 같다면 더 큰 수를 앞에 오도록 배치합니다.
//정수가 담긴 배열 numlist와 정수 n이 주어질 때 numlist의 원소를 n으로부터 가까운 순서대로 정렬한 배열을 return하도록 solution 함수를 완성해주세요.
public class Solution94 {
	public static void main(String[] args) {
		Solution94 s = new Solution94();
		System.out.println(Arrays.toString(s.solution(new int[]{1, 2, 3, 4, 5, 6}, 4)));
		System.out.println(Arrays.toString(s.solution2(new int[]{1, 2, 3, 4, 5, 6}, 4)));
	}

	public int[] solution(int[] numlist, int n) {
		int[][] arrs = Arrays.stream(numlist).mapToObj(i -> new int[]{i, Math.abs(i - n)}).toArray(int[][]::new);
		Arrays.sort(arrs, (o1, o2) -> o1[1] == o2[1] ? Integer.compare(o2[0], o1[0]) : Integer.compare(o1[1], o2[1]));
		return Arrays.stream(arrs).mapToInt(arr -> arr[0]).toArray();
	}

	public int[] solution2(int[] numlist, int n) {
		return Arrays.stream(numlist).boxed().sorted((a, b) -> Math.abs(n-a) == Math.abs(n-b) ? b.compareTo(a) : Integer.compare(Math.abs(n-a), Math.abs(n-b))).mapToInt(Integer::intValue).toArray();
	}

}


