package com.example.java_.enum_.t3;

public enum Direction {
	EAST(1), SOUTH(5), WEST(-1), NORTH(-10);

	private final int val; // 정수를 저장할 필드(인스턴스 변수)를 추가
	Direction(int val) {this.val = val;} // 생성자를 추가

	public int getVal(){return val;}
}
