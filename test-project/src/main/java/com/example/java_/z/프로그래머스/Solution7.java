package com.example.java_.z.프로그래머스;

// 중복된 숫자 개수
public class Solution7 {
	public static void main(String[] args) {
		Solution7 s = new Solution7();

		System.out.println(s.solution(new int[]{3,1,2,14,12,4}, 14));
	}

	public int solution(int[] array, int n) {
		int answer = 0;
		for (int item:array){
			if(item == n)
				answer++;
		}
		return answer;
	}
}
