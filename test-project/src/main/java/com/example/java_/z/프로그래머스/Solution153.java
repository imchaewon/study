package com.example.java_.z.프로그래머스;
import java.util.Arrays;

//최댓값과 최솟값
//문제 설명
//문자열 s에는 공백으로 구분된 숫자들이 저장되어 있습니다. str에 나타나는 숫자 중 최소값과 최대값을 찾아 이를 "(최소값) (최대값)"형태의 문자열을 반환하는 함수, solution을 완성하세요.
//예를들어 s가 "1 2 3 4"라면 "1 4"를 리턴하고, "-1 -2 -3 -4"라면 "-4 -1"을 리턴하면 됩니다.
//
//제한 조건
//s에는 둘 이상의 정수가 공백으로 구분되어 있습니다.

//입출력 예
//s	return
//"1 2 3 4"	"1 4"
//"-2 -3 -4 -5"	"-4 -1"
//"-1 -1"	"-1 -1"
public class Solution153 {
	public static void main(String[] args) {

		Solution153 s = new Solution153();

		System.out.println(s.solution("1 2 3 4"));
		System.out.println(s.solution("-1 -2 -3 -4"));
		System.out.println(s.solution("-1 -1"));

	}

	public String solution(String s) {
		return Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).min().orElse(0) + " " + Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).max().orElse(0);
	}
}
