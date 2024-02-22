package com.example.java_.clone.cloneable.shallowCopy;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class Circle implements Cloneable{
	Point p; // 원점 - 참조변수
	double r; // 반지름

	public Circle clone(){
		Object obj = null;
		try {
			obj = super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Circle) obj;
	}
}
