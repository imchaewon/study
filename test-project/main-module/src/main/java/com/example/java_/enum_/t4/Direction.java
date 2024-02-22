package com.example.java_.enum_.t4;

public enum Direction {
	NORTH(1, "^"), EAST(2, ">"), SOUTH(3, "V"), WEST(4, "<");

	private static final Direction[] DIR_ARR = Direction.values();
	private final int val;
	private final String symbol;

	Direction(int val, String symbol) {
		this.val = val;
		this.symbol = symbol;
	}

	public int getVal(){
		return val;
	}
	public String getSymbol(){
		return symbol;
	}

	public static Direction of(int dir){
		if(dir < 1 || dir > 4)
			throw new IllegalArgumentException("Invalid value: " + dir);
		return DIR_ARR[dir - 1];
	}

	// 방향을 회전시키는 메서드. num의 값만큼 90도씩 시계방향으로 회전한다.
	public Direction rotate(int num){
		num = num % values().length;
		if(num < 0) num += values().length; // num이 음수일때는 시계반대 방향으로 회전
		return DIR_ARR[(val - 1 + num)];
	}

	public String toString(){
		return name() + getSymbol();
	}
}







