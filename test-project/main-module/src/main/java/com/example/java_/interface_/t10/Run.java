package com.example.java_.interface_.t10;

public class Run {
	public static void main(String[] args) {

		Run r = new Run();
		r.m1(new C1());
		r.m1(new C2());

	}

	void m1(I1 i1){
		i1.m1();
	}
}
