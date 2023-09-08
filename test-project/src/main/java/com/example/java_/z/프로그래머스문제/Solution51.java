package com.example.java_.z.프로그래머스문제;

import java.util.Arrays;

//주사위의 개수
//머쓱이는 직육면체 모양의 상자를 하나 가지고 있는데 이 상자에 정육면체 모양의 주사위를 최대한 많이 채우고 싶습니다.
//상자의 가로, 세로, 높이가 저장되어있는 배열 box와 주사위 모서리의 길이 정수 n이 매개변수로 주어졌을 때,
//상자에 들어갈 수 있는 주사위의 최대 개수를 return 하도록 solution 함수를 완성해주세요.
public class Solution51 {
	public static void main(String[] args) {
		Solution51 s = new Solution51();
		System.out.println(s.solution(new int[]{10, 8, 6}, 3));
		System.out.println(s.solution2(new int[]{10, 8, 6}, 3));
	}

	public int solution(int[] box, int n) {
		// 가로 사이즈에 맞춰 안에 들어갈수있는 최대 갯수저장
		int width = box[0] / n;
		// 세로 사이즈에 맞춰 안에 들어갈수있는 최대 갯수저장
		int height = box[1] / n;
		// 높이 사이즈에 맞춰 안에 들어갈수있는 최대 갯수저장
		int length = box[2] / n;

		// 세 변수를 곱한 값을 리턴
		return width * height * length;
	}
	public int solution2(int[] box, int n) {
		return Arrays.stream(box).map(i -> i / n).reduce((x,y) -> x * y).orElse(0);
	}

}
