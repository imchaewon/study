package com.example.java_.generic.t1;

public class Run {
	public static void main(String[] args) {
		Box b = new Box();
		Box2<String> b2 = new Box2<>();

		b.item = "asd";
		String.valueOf(b.item).substring(1, 2);

		b2.item = "asd";
		b2.item.substring(1, 2);

	}
}
