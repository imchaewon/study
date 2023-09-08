package com.example.java_.annotation.override;

public class Run {
}

class Parent{
	void parentMathod(){}
}

class Child extends Parent{
//	@Override // Override 애너테이션을 붙이면 부모클래스에 애너테이션이 붙은 메소드명와 동일한 메소드가 없을경우 오류를 발생시켜줌
	void parentmathod(){}
}