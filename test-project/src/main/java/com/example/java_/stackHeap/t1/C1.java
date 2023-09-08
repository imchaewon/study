package com.example.java_.stackHeap.t1;

public class C1 {
	public void m1(){

		String s1 = "aa";
		String s2 = "bb";

		System.out.println(s1 == s2);

		String s3 = "aa";

		System.out.println(s1.equals(s3));
		System.out.println(s1 == s3);

		String s4 = "a";
		String s5 = "a";

		System.out.println(s1 == s4 + s5);
		System.out.println(s1.equals(s4 + s5));


		System.out.println("-----------");


		C2 c21 = new C2(); // new연산자로 인해서 C2객체가 heap영역에 저장되고 주소값x을 돌려받아서 stack영역에 저장됨
		C2 c22 = new C2(); // new연산자로 인해서 C2객체가 heap영역에 저장되고 주소값y을 돌려받아서 stack영역에 저장됨

		if (c21 == c22)
			System.out.println("같은 주소");
		else
			System.out.println("다른 주소");


		System.out.println("-----------");


		C2 a = null; // A타입의 a객체 선언 및 Stack 영역 공간 할당
		System.out.println(a); // 결과 : null
		a = new C2(); // Heap 메모리에 공간 할당 및 객체(a)에 참조값 할당
		System.out.println(a); // 결과 : @10f87f48




	}
}
