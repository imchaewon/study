package com.example.java_.class_.innerClass.t1;

import lombok.ToString;

public class C1 {
	void m1(){
		C2.m2();
		C2.m3();
	}
	@ToString
	static class C2{
		public int i1 = 123;
		public void m1(){
			System.out.println("C1C2m1");
		}
		static void m2(){
			System.out.println("C1C2m2");
		}

		private static void m3(){
			System.out.println("C1C2m3");
		}
	}
}
