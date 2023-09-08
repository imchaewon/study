package com.example.java_.stack_.t1;

import java.util.Stack;

public class Run {
	public static void main(String[] args) {

		Stack<String> stack = new Stack<>();

		stack.push("a");
		stack.push("b");
		stack.push("c");

		System.out.println(stack.peek());

		System.out.println(stack);

		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());

	}
}
