package com.example.java_.generic.t7;

import java.util.ArrayList;
import java.util.List;

public class Run {
	public static void main(String[] args) {

		List<Child> list = new ArrayList<>();
		m1(list);

		Class1<Child> p2 = new Class1<>();
		m2(p2);

		System.out.println(m3());

//		System.out.println(m4());

	}

	private static void m1(List<? extends Parent> p) {
		System.out.println(1111);
	}

	private static void m2(Class1<? extends Parent> p) {
		System.out.println(p.asd);
	}

	private static Class1<Integer> m3() {
		Class1<Integer> c1 = new Class1<>();
		c1.asd = 123;
		return c1;
	}

	/*public static <T>Class1<T> m4(String s1) { // 뭔가 데이터를(s1) 받아서 of메소드로 Class1의 객체를 만들고 돌려줌(정적 팩토리 메소드 구조)
		Class1<T> c1 = of(s1);
		return c1;
	}
	public static <T> ApiResponse<T> of(ResponseCode responseCode, T data) {
        return new ApiResponse<>(responseCode.getCode(), responseCode.getMessage(), data);
    }*/
}
