package com.example.java_.class_.constructor.t2;

public class Child extends Parent {
	private String name;

	public Child() {
		this("홍길동");
		System.out.println("Child() call");
	}
	public Child(String name) {
//		super(); 컴파일러가 자동 실행
		this.name = name;
		System.out.println("Child(String name) call");
	}
}
