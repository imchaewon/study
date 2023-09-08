package com.example.java_.stringUtils.compareTo.t2;

public class Run {
	public static void main(String[] args) {
		Integer i1 = 2,
			i2 = 4,
			i3 = -9;

		System.out.println(i1.compareTo(i2)); // 왼쪽값이 작으면 음수(-1) 반환
		System.out.println(Integer.compare(i1, i2)); // 왼쪽값이 작으면 음수(-1) 반환

		System.out.println(i2.compareTo(i3)); // 왼쪽값이 크면 양수(1) 반환
		System.out.println(Integer.compare(i2, i3)); // 왼쪽값이 크면 양수(1) 반환

		System.out.println(i2.compareTo(4)); // 같으면 0반환
		System.out.println(Integer.compare(i2, 4)); // 같으면 0반환
	}
}
