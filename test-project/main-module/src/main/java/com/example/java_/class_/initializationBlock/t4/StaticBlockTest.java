package com.example.java_.class_.initializationBlock.t4;

public class StaticBlockTest {
	static int[] arr = new int[10];

	static{
		for (int i = 0; i < 10; i++) {
			arr[i] = (int)(Math.random() * 10);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++)
			System.out.printf("arr[%d]: %d\r\n", i, arr[i]);
	}
}
