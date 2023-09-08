package com.example.java_.class_.abstract_.t1;

public abstract class C2 extends C1 {
	@Override
	public String toString() {
		return "C1{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}

	public C2(){
		init();
	}

	abstract void init();



}
