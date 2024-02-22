package com.example.java_.class_.constructor.t1;

public class Bus extends Car{
	public Bus(String number, int speed){
		super(number,speed);
	}
}

// 상속받은 클래스에 기본생성자가 없고, 다른생성자가 있을경우 자식에서 그 생성자를 구현해야함