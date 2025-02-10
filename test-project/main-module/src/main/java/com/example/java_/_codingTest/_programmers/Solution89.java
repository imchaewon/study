package com.example.java_._codingTest._programmers;

import java.util.Arrays;

//직사각형 넓이 구하기
//2차원 좌표 평면에 변이 축과 평행한 직사각형이 있습니다.
//직사각형 네 꼭짓점의 좌표 [[x1, y1], [x2, y2], [x3, y3], [x4, y4]]가 담겨있는 배열 dots가 매개변수로 주어질 때,
//직사각형의 넓이를 return 하도록 solution 함수를 완성해보세요.
public class Solution89 {
	public static void main(String[] args) {
		Solution89 s = new Solution89();
		System.out.println(s.solution(new int[][]{{1, 1},{2, 1},{2, 2}, {1, 2}}));
		System.out.println(s.solution(new int[][]{{-1, -1}, {1, 1}, {1, -1}, {-1, 1}}));
	}

	public int solution(int[][] dots) {
		return (Arrays.stream(dots).mapToInt(i -> i[0]).max().orElse(0) - Arrays.stream(dots).mapToInt(i -> i[0]).min().orElse(0)) *
				(Arrays.stream(dots).mapToInt(i -> i[1]).max().orElse(0) - Arrays.stream(dots).mapToInt(i -> i[1]).min().orElse(0));
	}

}