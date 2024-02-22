package com.example.java_.collectionFramework.deque.t1;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Run {
	public static void main(String[] args) {
		Deque<Integer> arrayDeque = new ArrayDeque<>(Arrays.asList(1,2,3,4,5));
		System.out.println(arrayDeque);
		arrayDeque.add(6);
		System.out.println(arrayDeque);
		System.out.println(arrayDeque.pop());
		System.out.println(arrayDeque);
	}
}
