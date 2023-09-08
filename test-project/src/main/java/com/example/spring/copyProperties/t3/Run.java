package com.example.spring.copyProperties.t3;

public class Run {
	public static void main(String[] args) throws CloneNotSupportedException {
		Human human = new Human("asd",12,132.4f);
		Wrapper wrapper = human.clone();

		System.out.println(human);
		System.out.println(wrapper);
	}
}
