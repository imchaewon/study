package com.example.java_.variable.t2;

public class Run {
	public static void main(String[] args) {
		Run r = new Run();
		r.m1();
	}

	public void m1(){
		int i = 0;
		i = m2(i);
		System.out.println(i);
	}

	public int m2(int i){
		return ++i;
	}
}
