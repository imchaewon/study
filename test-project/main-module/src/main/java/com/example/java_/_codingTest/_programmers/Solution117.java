package com.example.java_._codingTest._programmers;

import java.util.Arrays;

//제일 작은 수 제거하기
//정수를 저장한 배열, arr 에서 가장 작은 수를 제거한 배열을 리턴하는 함수, solution을 완성해주세요.
//단, 리턴하려는 배열이 빈 배열인 경우엔 배열에 -1을 채워 리턴하세요. 예를들어 arr이 [4,3,2,1]인 경우는 [4,3,2]를 리턴 하고, [10]면 [-1]을 리턴 합니다.
public class Solution117 {
	public static void main(String[] args) {
		Solution117 s = new Solution117();
		System.out.println(Arrays.toString(s.solution(new int[]{4, 3, 2, 1})));
	}

	public int[] solution(int[] arr) {
		return arr.length == 1 ? new int[]{-1} : Arrays.stream(arr).filter(n -> n != Arrays.stream(arr).min().orElse(0)).toArray();
	}
}