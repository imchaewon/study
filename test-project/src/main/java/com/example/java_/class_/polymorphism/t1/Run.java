package com.example.java_.class_.polymorphism.t1;

public class Run {
	public static void main(String[] args) {

		CaptionTv c = new CaptionTv();
		Tv t = new CaptionTv();

		System.out.println(c.text);
//		System.out.println(t.text); // Tv타입의 참조변수로는 text를 호출할 수 없음
//		t.caption(); // 마찬가지로 호출 불가능

		CaptionTv c2 = (CaptionTv) t;
		System.out.println(c2.text); // 다시 다운캐스팅을 하면 호출 가능
		c2.caption(); // 다시 다운캐스팅을 하면 호출 가능

		Tv t2 = new Tv();
		CaptionTv c3 = (CaptionTv) t2; // 컴파일은 통과, 하지만 런타임 오류 발생. 조상타입의 인스턴스를 자손타입의 참조변수로 참조하는 것은 허용되지 않음.
		// 컴파일 시에는 참조변수간의 타입만 체크하기 때문에 실행 시 생성될 인스턴스의 타입에 대해서는 전혀 알지 못한다. 그래서 컴파일 시에는 문제가 없었지만, 실행 시에는 에러가 발생함

	}
}

class Tv{
	boolean power; // 전원상태(on/off)
	int channel; // 채널

	void power(){
		power = !power;
	}
	void channelUp(){
		++channel;
	}
	void channelDown(){
		--channel;
	}
}

class CaptionTv extends Tv{
	String text; // 캡션을 보여주기 위한 문자열
	void caption(){
		// 내용 생략
	}
}