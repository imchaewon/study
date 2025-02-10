package com.example.java_._codingTest._programmers;

import java.util.stream.IntStream;

//저주의 숫자 3
//3x 마을 사람들은 3을 저주의 숫자라고 생각하기 때문에 3의 배수와 숫자 3을 사용하지 않습니다. 3x 마을 사람들의 숫자는 다음과 같습니다.
public class Solution95 {
	public static void main(String[] args) {
		Solution95 s = new Solution95();
		System.out.println("1\r\n" + s.solution(1) + "\r\n");
		System.out.println("2\r\n" + s.solution(2) + "\r\n");
		System.out.println("3\r\n" + s.solution(3) + "\r\n");
		System.out.println("4\r\n" + s.solution(4) + "\r\n");
		System.out.println("5\r\n" + s.solution(5) + "\r\n");
		System.out.println("6\r\n" + s.solution(6) + "\r\n");
		System.out.println("7\r\n" + s.solution(7) + "\r\n");
		System.out.println("8\r\n" + s.solution(8) + "\r\n");
		System.out.println("9\r\n" + s.solution(9) + "\r\n");
		System.out.println(s.solution(13));
	}

	public int solution(int n) {
//		System.out.println(Arrays.toString(IntStream.rangeClosed(1, n * 2).filter(i -> !(i % 3 == 0 || String.valueOf(i).contains("3"))).toArray()));
		return IntStream.rangeClosed(1, n * 2).filter(i -> !(i % 3 == 0 || String.valueOf(i).contains("3"))).toArray()[n - 1];
	}

}