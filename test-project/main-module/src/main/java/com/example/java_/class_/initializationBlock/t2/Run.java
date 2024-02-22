package com.example.java_.class_.initializationBlock.t2;

public class Run {
	public static void main(String[] args) {
		System.out.println(111);
		C1.m1();
		System.out.println(222);
		C1.m1();
		System.out.println(333);
	}
}

class C1{
	static{
		// 클래스 초기화 블럭
		System.out.println("static{}");
	}
	{
		// 인스턴스 초기화 블럭
		System.out.println("{}");
	}

	public C1(){
		System.out.println("생성자");
	}

	static void m1(){
		System.out.println("m1");
	}
}