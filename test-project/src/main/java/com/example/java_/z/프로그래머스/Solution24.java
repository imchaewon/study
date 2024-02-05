package com.example.java_.z.프로그래머스;

// 점의 위치 구하기
// 사분면은 한 평면을 x축과 y축을 기준으로 나눈 네 부분입니다. 사분면은 아래와 같이 1부터 4까지 번호를매깁니다.
// x 좌표와 y 좌표가 모두 양수이면 제1사분면에 속합니다.
// x 좌표가 음수, y 좌표가 양수이면 제2사분면에 속합니다.
// x 좌표와 y 좌표가 모두 음수이면 제3사분면에 속합니다.
// x 좌표가 양수, y 좌표가 음수이면 제4사분면에 속합니다.
public class Solution24 {
	public static void main(String[] args) {
		Solution24 s = new Solution24();
		System.out.println(s.solution(new int[]{2, 4}));
	}
	public int solution(int[] dot) {
		int answer = 0;
		if(dot[0] > 0) {
			if (dot[1] > 0) answer = 1;
			if (dot[1] < 0) answer = 3;
		}
		if (dot[0] < 0){
			if(dot[1] > 0) answer = 2;
			if (dot[1] < 0) answer = 4;
		}
		return answer;
	}
}
