package com.example.java_.ramda.람다안쓰고2;

public class Run {
	public static void main(String[] args) {


		Inter inter = new Inter() {

			@Override
			public int m1(int aaa) {
				return aaa + 2;
			}

			@Override
			public int m1(int aaa, int bbb) {
				return aaa + bbb;
			}

		};

//		Inter inter2 = (int ii) -> {}; 람다는 함수형인터페이스에서만 쓸 수 있음

		System.out.println(inter.m1(2));
		System.out.println(inter.m1(2,3));

	}
}
