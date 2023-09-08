package com.example.java_.class_.anonymousClass.t2;

import com.example.java_.class_.anonymousClass.Animal;

class Creature {
	// 필드에 익명자식객체를 생성하여 이용
	Animal dog = new Animal(){
		public void bark(){
			System.out.println("멍멍");
		}
	};

	void m1(){
		dog.bark();
	}

	void m2(){
		dog.bark();
	}
}