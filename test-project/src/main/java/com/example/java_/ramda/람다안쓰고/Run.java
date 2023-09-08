package com.example.java_.ramda.람다안쓰고;

public class Run {
	public static void main(String[] args) {

		Inter inter1 = new Inter() {

			@Override
			public int m1(int aaa) {
				return aaa + 2;
			}

		};

		Inter inter2 = (int aaa) -> {
			System.out.println(aaa+3);
			return aaa + 4;
		};


		Cla c = new Cla();
		Inter inter3 = c::test1;
		Inter inter4 = Cla::test2;

		System.out.println(inter1.m1(2));
		System.out.println(inter2.m1(2));
		System.out.println(inter3.m1(5));
		System.out.println(inter4.m1(5));

	}
}
