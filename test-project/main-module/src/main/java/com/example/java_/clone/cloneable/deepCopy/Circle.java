package com.example.java_.clone.cloneable.deepCopy;

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
		Circle c = (Circle) obj;
		c.p = new Point(this.p.x, this.p.y);
		return c;
	}
}
