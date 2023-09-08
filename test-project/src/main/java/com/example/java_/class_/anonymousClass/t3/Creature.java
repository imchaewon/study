package com.example.java_.class_.anonymousClass.t3;

class Animal2{
	String bark(){
		return "동물이 짖는다";
	}
}

class Creature{
	void m1(){
		Animal2 dog = new Animal2(){
			String bark(){
				return "멍멍";
			}
		};
		dog.bark();
	}
}
