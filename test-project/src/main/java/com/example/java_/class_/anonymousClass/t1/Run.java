package com.example.java_.class_.anonymousClass.t1;

import com.example.java_.class_.anonymousClass.Animal;

public class Run {
	public static void main(String[] args) {

		Animal cat = new Animal(){
			@Override
			public void bark(){
				System.out.println("야옹");
				jump(); // 오버라이딩한 메소드 말고, 새로 생성한 메소드는 익명클래스 내부에서만 사용 가능
			}

			void jump(){
				System.out.println("점프하기");
			}
		}; // 단 익명클래스는 끝에 세미콜론은 반드시 붙여야 한다

		cat.bark();
//		cat.jump(); // 오버라이딩한 메소드 말고, 새로 생성한 메소드는 외부에서 사용불가

	}
}
