package com.example.java_.enum_.t2;

public class Unit {
	int x, y;
	Direction dir;

	void init(){
		dir = Direction.EAST;

		if(dir == Direction.EAST) {
			x++;
//		}else if(dir > Direction.WEST) // 에러. 열거형 상수에 비교연산자 사용불가
		}else if(dir.compareTo(Direction.WEST) > 0){ // compareTo() 는 가능
		}

		switch (dir){
			case EAST: x++; break;
			case WEST: x--; break;
			case SOUTH: y++; break;
			case NORTH: y--; break;
		}

		String name = Direction.WEST.name();
		System.out.println(name.equals("WEST"));

		Direction d = Direction.valueOf("WEST");
		System.out.println(d == Direction.WEST);
	}

	public static void main(String[] args) {
		new Unit().init();
	}
}


