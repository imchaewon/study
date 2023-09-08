package com.example.java_.class_.anonymousClass.t4;

import com.example.java_.class_.anonymousClass.Animal;


public class Run {
	public static void main(String[] args) {

		Run r = new Run();

		r.m1(new Animal(){
			public void bark(){
				System.out.println("멍멍");
			}
		});

	}

	public void m1(Animal dog){
		dog.bark();
	}
}