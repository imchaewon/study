package com.example.java_.class_.initializationBlock.t7;

public class Run {
	public static void main(String[] args) {
		InitTest initTest = new InitTest();
		System.out.println(initTest.cv);
		System.out.println(initTest.iv);
	}
}
class InitTest{
	static int cv = 1;
	int iv = 1;

	static{
		cv = 2;
	}
	InitTest(){
		cv = 3;
	}
}