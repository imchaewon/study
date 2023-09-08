package com.example.java_.class_.t6;

public class Run {
	public static void main(String[] args) {

		Run r = new Run();
		r.m1();

	}

	private void m1() {

		C1 c = new C1();
		System.out.println(c.s1);
		c.m1();

		System.out.println("------------------------------");

		System.out.println(c.s2);
		c.m2();

		System.out.println("------------------------------");

		System.out.println(C1.s2);
		C1.m2();
	}
}

class C1{
	String s1 = "instance variable";
	static String s2 = "class variable";

	void m1(){
		System.out.println("instance method");
		System.out.println(s1); // 인스턴스 변수 사용 가능
		System.out.println(s2); // 클래스 변수 사용 가능
	}

	static void m2(){
		System.out.println("class method");
//		System.out.println(s1); // 인스턴스 변수 사용 불가
	}
}