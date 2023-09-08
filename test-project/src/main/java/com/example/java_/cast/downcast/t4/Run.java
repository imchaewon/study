package com.example.java_.cast.downcast.t4;

public class Run {
	public static void main(String[] args) {

		Animal a = new Animal();
//		System.out.println((Cat) a); // 업캐스팅되지 않은 객체를 다운캐스팅시 오류 발생

		Animal a2 = new Cat();
		System.out.println((Animal) a2);

	}
}
