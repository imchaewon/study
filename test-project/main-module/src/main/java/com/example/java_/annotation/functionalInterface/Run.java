package com.example.java_.annotation.functionalInterface;

public class Run {
	public static void main(String[] args) {
		Runnable r = () -> {};
	}
}

@FunctionalInterface
interface i{
	void m1();
}