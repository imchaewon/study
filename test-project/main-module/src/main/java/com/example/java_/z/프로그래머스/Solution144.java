package com.example.java_.z.프로그래머스;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//모의고사
//수포자는 수학을 포기한 사람의 준말입니다. 수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 합니다. 수포자는 1번 문제부터 마지막 문제까지 다음과 같이 찍습니다.
//1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
//2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
//3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
//1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때, 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.
public class Solution144 {
	public static void main(String[] args) {
		Solution144 s = new Solution144();
		System.out.println(Arrays.toString(s.solution(new int[]{1, 2, 3, 4, 5})));
		System.out.println(Arrays.toString(s.solution(new int[]{1,3,2,4,2})));
		System.out.println(Arrays.toString(s.solution(new int[]{3,3,1,1,2,2,4,4,5,5})));
	}

	public int[] solution(int[] answers) {
		int[] result = new int[3];
		List<Integer> list = new ArrayList<>();

		int[][] arr = {{1,2,3,4,5}, {2,1,2,3,2,4,2,5}, {3,3,1,1,2,2,4,4,5,5}};

		for (int i = 0; i < answers.length; i++) {
			result[0] += arr[0][i % arr[0].length] == answers[i] ? 1 : 0;
			result[1] += arr[1][i % arr[1].length] == answers[i] ? 1 : 0;
			result[2] += arr[2][i % arr[2].length] == answers[i] ? 1 : 0;
		}

//		System.out.println(Arrays.toString(result));

		int max = 0;
		for (int i = 0; i < result.length; i++)
			if(result[i] >= max){
				if(result[i] > max)
					list.clear();
				list.add(i + 1);
				max = result[i];
			}

		return list.stream().mapToInt(Integer::intValue).toArray();
	}
}



