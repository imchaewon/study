package com.example.java_.collectionFramework.queue.t1;

import java.util.LinkedList;
import java.util.Queue;

public class Run {
	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1);
		queue.offer(2);
		queue.offer(3);

		while(!queue.isEmpty()){
			System.out.println(queue.poll());
		}
	}
}
