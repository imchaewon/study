package com.example.java_.class_.t8;

public class Run {
	public static void main(String[] args) {
		new C1("aaa",12).m1();
		String s1 = new C1("aaa",12).c2m1(); // 상속받은 메소드 쓸 수 있음
		System.out.println(new C1("aaa",12).m2()); // 객체 반환 이때 toString이 자신한테 없고 부모한테 있으면 부모의 toString이 적용됨

		System.out.println(new C1("aaa",12).m2().m3().c2m1());


	}
}
