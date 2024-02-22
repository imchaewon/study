package com.example.java_.integerUtils.compare.t1;

public class Run {
	public static void main(String[] args) {

		int a = 55;
		int b = 55;
		int c = 110;
		int d = 150;
		int e = -8;

		System.out.println(Integer.compare(a,b));
		System.out.println(Integer.compare(a,c));
		System.out.println(Integer.compare(a,d));
		System.out.println(Integer.compare(a,e));

		System.out.println("------------------------------");

		System.out.println(Integer.compareUnsigned(a,b));
		System.out.println(Integer.compareUnsigned(a,c));
		System.out.println(Integer.compareUnsigned(a,d));
		System.out.println(Integer.compareUnsigned(a,e));

		System.out.println("------------------------------");

		System.out.println(Integer.max(a,c));
		System.out.println(Integer.min(a,c));
		System.out.println(Integer.SIZE);
		String s = Integer.toString(a);
		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toOctalString(a));
		System.out.println(Integer.toHexString(a));
		System.out.println(Integer.toUnsignedString(a));

	}
}
