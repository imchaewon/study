package com.example.java_.assert_.t3;

import lombok.AllArgsConstructor;

public class Run {
	public static void main(String[] args) {
//		double result = new MetricsCalculator().xProjection(new Point(1), new Point(2));
		double result = new MetricsCalculator().xProjection(null, new Point(2));
		System.out.println("result = " + result);
	}
}

class MetricsCalculator{
	public double xProjection(Point p1, Point p2){
		assert p1 != null : "p1 should not be null";
		assert p2 != null : "p2 should not be null";
		return (p2.x - p1.x) * 1.5;
	}
}

@AllArgsConstructor
class Point {
	int x;
}