package com.example.java_.stream.intermediate.reduce.parallel.minus;

import java.util.stream.Stream;

public class Mechanism {
	public static void main(String args[]) {
		int i1 = Stream.of(1,2,3,4).parallel().reduce(0,(i,j)->{
			System.out.println(Thread.currentThread().getName() + " : " + i + "-" + j + "="+(i-j));
			return i-j;
		});
		System.out.println(i1);

		System.out.println("------------------------------");

		int i2 = Stream.of(1,2,3,4).parallel().reduce(0,(i,j)->{
			System.out.println(Thread.currentThread().getName() + " : " + i + "-" + j + "="+(i-j));
			return i-j;
		},(x,y)-> {
			System.out.printf(Thread.currentThread().getName()+": %d + %d = %d\r\n", x, y, x+y);
			return x + y;
		});
		System.out.println(i2);

		System.out.println("------------------------------");

		int i3 = Stream.of(1,2,3,4,5).parallel().reduce(0,(i,j)->{
			System.out.println(Thread.currentThread().getName() + " : " + i + "-" + j + "="+(i-j));
			return i-j;
		});
		System.out.println(i3);

		System.out.println("------------------------------");

		int i4 = Stream.of(1,2,3,4,5,6).parallel().reduce(0,(i,j)->{
			System.out.println(Thread.currentThread().getName() + " : " + i + "-" + j + "="+(i-j));
			return i-j;
		});
		System.out.println(i4);

		System.out.println("------------------------------");

		int i5 = Stream.of(1,2,3,4,5,6,7,8).parallel().reduce(0,(i,j)->{
			System.out.println(Thread.currentThread().getName() + " : " + i + "-" + j + "="+(i-j));
			return i-j;
		});
		System.out.println(i5);

		System.out.println("------------------------------");

		int i6 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
				.parallel().reduce(0, (p, n) -> {
					System.out.printf("p: %d\tn: %d\tp-n: %d\n", p, n, p-n);
					return p - n;
				}, (x,y) -> x+y);
		System.out.println(i6);

		System.out.println("------------------------------");

		int i7 = Stream.of(1,2,3,4,5,6,7,8,9,10).parallel().reduce(0,(i,j)->{
			System.out.println(Thread.currentThread().getName() + " : " + i + "-" + j + "="+(i-j));
			return i-j;
		});
		System.out.println(i7);
	}
}
