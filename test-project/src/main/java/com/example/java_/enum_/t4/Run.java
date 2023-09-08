package com.example.java_.enum_.t4;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {
		for (Direction d : Direction.values())
			System.out.printf("%s=%d%n", d.name(), d.getVal());

		Direction d1 = Direction.NORTH;
		Direction d2 = Direction.of(1);

		System.out.printf("d1=%s, %d%n", d1.name(), d1.getVal());
		System.out.printf("d1=%s, %d%n", d2.name(), d1.getVal());

		System.out.println(Direction.NORTH.rotate(1));
		System.out.println(Direction.NORTH.rotate(2));
		System.out.println(Direction.NORTH.rotate(-1));
		System.out.println(Direction.NORTH.rotate(-2));
		System.out.println(Direction.NORTH.rotate(-5));
	}
}
