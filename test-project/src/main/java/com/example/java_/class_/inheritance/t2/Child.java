package com.example.java_.class_.inheritance.t2;

import java.util.ArrayList;
import java.util.List;

public class Child {

	private List<Parent> list;

	public void m1(){
		list = new ArrayList<>();
		list.add(new Parent());
		list.get(0).m1();

	};

	public static void main(String[] args) {
		Child c = new Child();
		c.m1();
	}
}
