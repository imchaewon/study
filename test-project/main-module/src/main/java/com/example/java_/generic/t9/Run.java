package com.example.java_.generic.t9;

public class Run {
	public static void main(String[] args) {
		Box box = new Box();
		box.m1(new Integer[]{1,2,3});
	}
}

class Box{
	public <T> void m1(T[] arr){
		for (T element : arr) {
			System.out.println(element);
		}
	}
}
