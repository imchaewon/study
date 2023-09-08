package com.example.java_.gofDesignPatterns.creational.builder.t3;

public class Run {
	public static void main(String[] args) {

		C1.Builder builder = new C1.Builder(240, 8);
		builder.calories(100);
		builder.sodium(35);
		builder.carbohydrate(27);
		C1 o1 = builder.build();

		System.out.println(o1);

		// 각 줄마다 builder를 타이핑하지 않아도 되어 편리하다.
		C1 o2 = new C1.Builder(120, 4)    // 필수값 입력
				.calories(50)
				.sodium(60)
				.carbohydrate(70)
				.build();           // build() 가 객체를 생성해 돌려준다.

		System.out.println(o2);

	}
}
