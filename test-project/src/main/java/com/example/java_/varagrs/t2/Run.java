package com.example.java_.varagrs.t2;

import java.util.Arrays;

public class Run {
	public static void main(String[] args) {
		m1("aa");
		m1("aa",1);
		m1("aa",1,2);
	}

	static void m1(String s1, int... i1){
		System.out.print(s1 + ", ");
		System.out.print(Arrays.toString(i1));
		System.out.println();
	}

//	static void m2(int... i1, String s1){ // 가변인수가 불가능
//	}
}
