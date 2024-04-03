package com.example.java_.gofDesignPatterns.structural.adapter.t1;

public class DuckTestDrive {
	public static void main(String[] args) {
		MallardDuck duck = new MallardDuck();

		WildTurkey turkey = new WildTurkey();
		Duck turkeyAdapter = new TurkeyAdapter(turkey);

		System.out.println("칠면조가 말하기를..");
		turkey.gobble();
		turkey.fly();

		System.out.println();
		System.out.println("오리가 말하기를..");
		testDuck(duck);

		System.out.println();
		System.out.println("칠면조어댑터 객체를 넣어 호출..");
		testDuck(turkeyAdapter);
	}

	public static void testDuck(Duck duck) {
		duck.quack();
		duck.fly();
	}
}
