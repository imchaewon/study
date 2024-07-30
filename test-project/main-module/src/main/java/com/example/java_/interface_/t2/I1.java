package com.example.java_.interface_.t2;

public interface I1 {
	int test();

	default int test2(int a) {
		return a + 2;
	}
//	int test2(int a); // default메소드와 일반 메소드를 동시에 쓰는건 불가

}