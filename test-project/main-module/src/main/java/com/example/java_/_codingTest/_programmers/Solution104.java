package com.example.java_._codingTest._programmers;

import java.util.ArrayList;
import java.util.List;

//평행
//점 네 개의 좌표를 담은 이차원 배열  dots가 다음과 같이 매개변수로 주어집니다.
//[[x1, y1], [x2, y2], [x3, y3], [x4, y4]]
//주어진 네 개의 점을 두 개씩 이었을 때, 두 직선이 평행이 되는 경우가 있으면 1을 없으면 0을 return 하도록 solution 함수를 완성해보세요.
public class Solution104 {
	public static void main(String[] args) {
		Solution104 s = new Solution104();
		System.out.println(s.solution(new int[][]{{1, 4},{9, 2},{3, 8},{11, 6}}));
	}

	public int solution(int[][] dots){
		List<Double> list = new ArrayList<>();
		for (int i = 0; i < dots.length - 1; i++)
			for (int j = i+1; j < dots.length; j++) {
				double sideX = dots[i][0] - dots[j][0];
				double sideY = dots[i][1] - dots[j][1];;
				double slop = sideY / sideX;
				if (list.contains(slop))
					return 1;
				list.add(slop);
			}
		return 0;
	}

}