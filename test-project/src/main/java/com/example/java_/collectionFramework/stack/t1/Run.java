package com.example.java_.collectionFramework.stack.t1;

import java.util.Stack;

public class Run {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack();
		stack.push(0);
		stack.push(1);
		stack.push(2);

		System.out.println(stack);
		System.out.println(stack.empty());
		System.out.println(stack.search(2)); // 값으로 Top에서부터 시작하는 주소 반환. 주소는 0부터가 아닌 1부터 시작
		System.out.println(stack.peek()); // 값을 꺼내지는 않고 조회만 함
		System.out.println(stack.peek()); // 값을 꺼내지는 않고 조회만 함
		System.out.println(stack);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack);
		System.out.println(stack.empty());
		System.out.println(stack.push(1));
		stack.clear();
	}
}
