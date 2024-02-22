package com.example.java_.z.프로그래머스;

//다음에 올 숫자
//등차수열 혹은 등비수열 common이 매개변수로 주어질 때, 마지막 원소 다음으로 올 숫자를 return 하도록 solution 함수를 완성해보세요.
public class Solution99 {
	public static void main(String[] args) {
		Solution99 s = new Solution99();
		System.out.println(s.solution(new int[]{1, 2, 3, 4}));
		System.out.println(s.solution(new int[]{-1, -2, -3, -4}));
		System.out.println(s.solution(new int[]{2, 0, -2}));
		System.out.println(s.solution(new int[]{2, -2, -6}));
		System.out.println(s.solution(new int[]{2, 4, 8}));
		System.out.println(s.solution(new int[]{-2, -4, -8}));
	}

	public int solution(int[] common) {
		return common[1] != 0 && common[0] / (double) common[1] == common[1] / (double) common[2] ? (int) (common[common.length - 1] * (common[1] / (double) common[0])) : common[common.length - 1] + common[1] - common[0];
	}

}










