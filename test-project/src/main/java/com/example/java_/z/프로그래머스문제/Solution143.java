package com.example.java_.z.프로그래머스문제;

import java.util.Arrays;
import java.util.stream.IntStream;

//소수 찾기
//1부터 입력받은 숫자 n 사이에 있는 소수의 개수를 반환하는 함수, solution을 만들어 보세요.
//소수는 1과 자기 자신으로만 나누어지는 수를 의미합니다.
//(1은 소수가 아닙니다.)
//제한 조건
//n은 2이상 1000000이하의 자연수입니다.
public class Solution143 {
	public static void main(String[] args) {
		Solution143 s = new Solution143();
		System.out.println(s.solution(10));
		System.out.println(s.solution(5));
	}

	public int solution(int n) {
		return (int) IntStream.rangeClosed(2, n).filter(i -> IntStream.range(2, i).noneMatch(i2 -> i % i2 == 0)).count();
	}

	public int solution2(int n) {
		int answer = 0;

		// 에라토스테네스의 체
		int[] filter = new int[n + 1];
		Arrays.fill(filter, 1);
		// 0, 1은 소수가 아님
		filter[0] = 0;
		filter[1] = 0;

		for (int i = 2; i < Math.sqrt(n) + 1; i++){

			if (filter[i] == 1){
				for (int j = i * 2; j <= n; j+=i){
					filter[j] = 0;
				}
			}
		}

		for (int check: filter){
			if (check == 1){
				answer++;
			}
		}

		return answer;
	}
}



