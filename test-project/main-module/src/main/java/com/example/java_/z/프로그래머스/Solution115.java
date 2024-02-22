package com.example.java_.z.프로그래머스;

import java.util.Arrays;

//나누어 떨어지는 숫자 배열
//array의 각 element 중 divisor로 나누어 떨어지는 값을 오름차순으로 정렬한 배열을 반환하는 함수, solution을 작성해주세요.
//divisor로 나누어 떨어지는 element가 하나도 없다면 배열에 -1을 담아 반환하세요.
public class Solution115 {
	public static void main(String[] args) {
		Solution115 s = new Solution115();
		System.out.println(Arrays.toString(s.solution(new int[]{5, 9, 7, 10}, 5)));
		System.out.println(Arrays.toString(s.solution(new int[]{2, 36, 1, 3}, 1)));
		System.out.println(Arrays.toString(s.solution(new int[]{3,2,6}, 10)));
	}

	public int[] solution(int[] arr, int divisor) {
		return Arrays.stream(arr).noneMatch(n -> n % divisor == 0) ? new int[]{-1} : Arrays.stream(arr).filter(n -> n % divisor == 0).sorted().toArray();
	}
}



