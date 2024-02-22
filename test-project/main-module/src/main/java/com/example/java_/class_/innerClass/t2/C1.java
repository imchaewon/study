package com.example.java_.class_.innerClass.t2;

import lombok.ToString;

public class C1 {
	void m1(){
		C2 c = new C2();
		c.m1();
		c.m2();
	}
	@ToString
	class C2{
		public int i1 = 123;
		public void m1(){
			System.out.println("C1C2m1");
		}
		private void m2(){
			System.out.println("C1C2m2");
		}
	}
}
