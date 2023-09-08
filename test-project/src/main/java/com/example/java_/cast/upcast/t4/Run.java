package com.example.java_.cast.upcast.t4;

public class Run {
	public static void main(String[] args) {

		Circle[] c = new Circle[2];
		c[0] = new Circle();
		c[1] = new Circle();

		Rectangle[] r = new Rectangle[2];
		r[0] = new Rectangle();
		r[1] = new Rectangle();

		Triangle[] t = new Triangle[2];
		t[0] = new Triangle();
		t[1] = new Triangle();

		// 위 코드를 업 캐스팅을 이용해 아래처럼 하나의 타입으로 묶어 배열 구성이 가능. 가독성 및 유지보수성 좋아짐
		// 자식클래스에만 있는 고유한 메소드를 실행하려면, 오버라이딩한 메소드가 아닌 이상, 다시 자식클래스로 다운캐스팅을 해야함

		Shape[] s = new Shape[6];
		s[0] = new Circle();
		s[1] = new Circle();
		s[2] = new Rectangle();
		s[3] = new Rectangle();
		s[4] = new Triangle();
		s[5] = new Triangle();

	}
}
