package com.example.java_.object_utils.equals;

import java.util.Objects;

public class Run {
	public static void main(String[] args) {
		String a = null;
		String b = null;

		// 원래라면 먼저 null인지 검사를 했어야함
		if(a != null && a.equals(b))
			System.out.println(a.equals(b));

		// a가 null일때는 에러가 나기때문
//		System.out.println(a.equals(b));

		// Objects.equals를 쓰면 null검사를 안하고도 가능 (내부적으로 null검사를 함)
		System.out.println(Objects.equals(a,b));
	}
}
