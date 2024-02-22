package com.example.java_.class_.static_.t1;

public final class FinalTest { // 상속 불가
	public final int MAX_SIZE = 10; // 재할당 불가

	public final int getMaxSize(){ // 오버라이딩 불가
		final int LV = MAX_SIZE;
		return MAX_SIZE;
	}

}

//class InheritanceTest extends FinalTest{}
