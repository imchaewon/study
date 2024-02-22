package com.example.java_.ramda.t3;

// 매개변수 O, 반환 X
public class OneParameNoReturn {
	public static void main(String[] args) {

		Printable p;

		p = (String s) -> { System.out.println(s); }; // 줄임 없는 표현
		p.print("람다 1");

		p = (String s) -> System.out.println(s); // 중괄호 생략
		p.print("람다 2");

		p = (s) -> System.out.println(s); // 매개변수형 생략
		p.print("람다 3");

		p = s -> System.out.println(s); // 매개변수 소괄호 생략
		p.print("람다 4");

		p = System.out::println; // 메소드 레퍼런스
		p.print("람다 5");

	}
}
