package com.example.java_.variable.t1;

public class Run {
	public static void main(String[] args) {
		Run r = new Run();
		r.m1();
	}

	public void m1(){
		int i = 0;
		m2(i);
		System.out.println(i);
	}

	public void m2(int i){
		i++;
	}
}
