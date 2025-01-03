package com.example.java_._codingTest._programmers;

import java.util.Arrays;

//2차원으로 만들기
//정수 배열 num_list와 정수 n이 매개변수로 주어집니다. num_list를 다음 설명과 같이 2차원 배열로 바꿔 return하도록 solution 함수를 완성해주세요.
//num_list가 [1, 2, 3, 4, 5, 6, 7, 8] 로 길이가 8이고 n이 2이므로 num_list를 2 * 4 배열로 다음과 같이 변경합니다. 2차원으로 바꿀 때에는 num_list의 원소들을 앞에서부터 n개씩 나눠 2차원 배열로 변경합니다.
public class Solution69 {
	public static void main(String[] args) {
		Solution69 s = new Solution69();
		System.out.println(Arrays.deepToString(s.solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 3)));
	}

	public int[][] solution(int[] num_list, int n) {
		int r = num_list.length / n + (num_list.length % n == 0 ? 0 : 1);
		int[][] answer = new int[r][n];

		for (int i = 0; i < r; i++)
			for (int j = 0; j < n; j++) {
				answer[i][j] = num_list[(j) + i * n];
				if(i * n + j + 1 == num_list.length) break;
			}

		return answer;
	}

}