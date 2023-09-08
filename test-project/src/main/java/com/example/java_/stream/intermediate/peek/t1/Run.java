package com.example.java_.stream.intermediate.peek.t1;

import java.util.Arrays;
import java.util.List;

public class Run {
	public static void main(String[] args) {

		m1();
		m2();

	}

	private static void m1() {
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);

		Inter inter = (a) -> {
			a += 1000;
			System.out.println(a + 100);
			return a + 10;
		};

		Inter2 inter2 = () -> 10;

		list.stream()
		.filter(i -> i % 2 == 0)
		.map(i -> i + 2)
//		.map(inter::calc) // 출력도 하고 return도 잘 받아와짐
//		.map(inter2::calc) // 파라미터가 없어서 메소드를 쓸수없음
		.peek(inter::calc) // 중간 처리 메소드. println은 되지만 return은 안먹힘 (값 변경 불가)
		.forEach(System.out::println);
	}

	private static void m2() {
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);

		Inter inter = (a) -> {
			System.out.println(a + 100);
			return a + 10;
		};

		Inter2 inter2 = () -> 10;

		list.stream()
		.filter(i -> i % 2 == 0)
		.map(i -> i + 2)
//		.map(inter::calc) //출력도 하고 return도 잘 받아와짐
//		.map(inter2::calc) // 파라미터가 없어서 메소드를 쓸수없음
		.peek(inter::calc) // 중간 처리 메소드. println은 되지만 return은 안먹힘
		.peek(inter::calc) // 중간 처리 메소드이기때문에 마지막에 오면 동작을 안함
		.peek(inter::calc); // 중간 처리 메소드이기때문에 마지막에 오면 동작을 안함
	}

}
