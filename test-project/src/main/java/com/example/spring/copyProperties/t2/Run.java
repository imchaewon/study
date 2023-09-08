package com.example.spring.copyProperties.t2;

public class Run {
	public static void main(String[] args) throws CloneNotSupportedException {
		Human human = new Human("asd",12,132.4f);
		Clone clone = human.doClone();

		System.out.println(human);
		System.out.println(clone);
	}
}
