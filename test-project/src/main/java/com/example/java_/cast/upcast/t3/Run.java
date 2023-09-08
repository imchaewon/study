package com.example.java_.cast.upcast.t3;

public class Run {
	public static void main(String[] args) {

		Animal a = new Animal();
		a.bark();

		Cat c = new Cat();
		c.bark();
		c.jump();

		((Animal) c).bark(); // 업캐스팅을 해도 자식클래스에서 오버라이딩한 메소드는 유지 (부모클래스의 메소드가 아니라 자식클래스의 메소드가 실행됨)
//		((Animal) c).jump(); // 업캐스팅시 자식클래서 새로 생성한 메소드/필드는 사용 불가

	}
}
