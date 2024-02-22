package com.example.java_.interface_.t12;

public class SCV extends GroundUnit implements Repairable{
	void repair(Tank t){
		// Tank를 수리한다
	}
	void repair(Dropship d){
		// Dropship을 수리한다
	}
	void repair(GroundUnit gu){
		// 매개변수로 넘겨진 지상유닛(GroundUnit)을 수리한다.
	}
	void repair(AirUnit au){
		// 매개변수로 넘겨진 공중유닛(AirUnit)을 수리한다.
	}
	void repair(Repairable r){
		// 매개변수로 넘겨받은 유닛을 수리한다.
	}
}
