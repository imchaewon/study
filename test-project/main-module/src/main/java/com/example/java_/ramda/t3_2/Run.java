package com.example.java_.ramda.t3_2;

public class Run {
	public static void main(String[] args) {

		MathBox box = new MathBox();
		Function sum = box::sum; // 인스턴스 멤버이기 때문에 box라는 인스턴스를 통해서만 접근 가능
		Function mul = MathBox::mul; // static 멤버이기 때문에 MathBox라는 클래스를 통해서 접근이 가능

		System.out.println(sum.func(1, 2));
		System.out.println(mul.func(1, 2));

		Number n = new Number(2,3);
		n.print(mul);
		n.print(sum);


	}
}
