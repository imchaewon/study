package com.example.java_.class_.nasted.t1;

public class Outer {

	private String o_name = "outer";

	public class Inner{

		public String i_name = "inner";

//		public static void main(String[] args) {
//
//		}

		public void i_m1(){
			System.out.println(o_name);
			System.out.println(i_name);
		}

	}

	public static void main(String[] args) {
		Outer o = new Outer();
		o.o_m1();
	}

	public void o_m1(){
		Inner i = new Inner();
		i.i_m1();
	}

	public void o_m2(){
//		System.out.println(i_name);
	}

}
