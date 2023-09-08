package com.example.java_.class_.t10;

public class Run{
	public static void main(String[] args) {
		C1 c1 = new C1();
	}
}

class C1 {
	private C2 c2 = new C2();
}

class C2{
	public C2(){
		System.out.println("생성자블록...");
	}

	static {
		System.out.println("초기화블록...");
	}
}