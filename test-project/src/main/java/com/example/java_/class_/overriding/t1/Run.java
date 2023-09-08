package com.example.java_.class_.overriding.t1;

public class Run {
	public static void main(String[] args) {

		Child child = new Child();

		System.out.println(child.m1(1, 2));

	}

}
class Parent{
	int m1(int i1, int i2){
		return i1 + i2;
	}
}

class Child extends Parent{
	int m1(int i3, int i4){
		return i3 + i4;
	}
}
