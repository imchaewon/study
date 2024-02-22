package com.example.java_.exception_.t4;

public class Run {
	public static void main(String[] args) {
		method();
		System.out.println("method 끝");
	}

	private static void method() {
		try {
			System.out.println("method1이 호출되었습니다.");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("method1의 finally블럭이 실행되었습니다.");
		}
		System.out.println(123);
	}
}
