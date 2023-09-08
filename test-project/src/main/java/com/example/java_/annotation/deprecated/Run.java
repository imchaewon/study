package com.example.java_.annotation.deprecated;

public class Run {
	public static void main(String[] args) {
		C1 c = new C1();
		c.oldField = 10;
		System.out.println(c.getOldField());
	}
}
class C1{
	int newField;
	int getNewField(){
		return newField;
	}

	@Deprecated
	int oldField;
	@Deprecated
	int getOldField(){
		return oldField;
	}
}
