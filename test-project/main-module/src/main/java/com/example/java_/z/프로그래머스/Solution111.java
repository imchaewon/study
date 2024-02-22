package com.example.java_.z.프로그래머스;

//하샤드 수
//양의 정수 x가 하샤드 수이려면 x의 자릿수의 합으로 x가 나누어져야 합니다.
//예를 들어 18의 자릿수 합은 1+8=9이고, 18은 9로 나누어 떨어지므로 18은 하샤드 수입니다. 자연수 x를 입력받아 x가 하샤드 수인지 아닌지 검사하는 함수, solution을 완성해주세요.
public class Solution111 {
	public static void main(String[] args) {
		Solution111 s = new Solution111();
		System.out.println(s.solution(10));
		System.out.println(s.solution(12));
		System.out.println(s.solution(11));
		System.out.println(s.solution(13));
	}

	public boolean solution(int x) {
		return x % String.valueOf(x).chars().map(c -> Integer.parseInt(String.valueOf((char) c))).reduce(Integer::sum).orElse(0) == 0;
	}
}



