package com.example.java_.enum_.t5;

public enum Transportation {
	BUS(100){
		int fare(int distance){
			return distance * BASIC_FARE;
		}
	},
	TRAIN(150){int fare(int distance){return distance * BASIC_FARE;}},
	SHIP(100){int fare(int distance){return distance * BASIC_FARE;}},
	AIRPLANE(300){int fare(int distance){return distance * BASIC_FARE;}}; // 예제에서는 다 똑같이 했지만 다르게 할 수도 있음. 열거형에 추상메소드를 사용할일은 그렇게 많지는 않으니 참고만.

	protected final int BASIC_FARE; // 기본운송요금. protected로 해야 각 상수에서 접근이 가능해짐

	Transportation(int basicFare) {
		this.BASIC_FARE = basicFare;
	}

	int fare(){ // 기본운송요금을 반환
		return BASIC_FARE;
	}

	abstract int fare(int distance); // 거리에 따른 요금을 계산하는 추상 메소드
}
