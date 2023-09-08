package com.example.java_.exception_.t1;

public class Run {
	public static void main(String[] args) {

		try {
			test();
		} catch (CustomException e) {
			System.out.println("커스텀 예외 테스트");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void test() throws CustomException{
		throw new CustomException("예외 테스트 입니다.");
	}

}
