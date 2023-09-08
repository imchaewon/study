package com.example.java_.class_.inheritance.t1;

public class Child {

	public void m1(Parent p){
		p.m1();
	};

	public static void main(String[] args) {
		Child c = new Child();
		Parent p = new Parent();
		c.m1(p);
	}
}
