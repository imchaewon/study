package com.example.java_.gofDesignPatterns.structural.adapter.t1;

public class DuckTestDrive {
	public static void main(String[] args) {
		MallardDuck duck = new MallardDuck();

		WildTurkey turkey = new WildTurkey();
		Duck turkeyAdapter = new TurkeyAdapter(turkey);

		System.out.println("칠면조가 말하기를..");
		turkey.gobble();
		turkey.fly();

		System.out.println("\r\n오리가 말하기를..");
		testDuck(duck);

		System.out.println("\r\n칠면조어댑터로 호출..");
		testDuck(turkeyAdapter);
	}

	public static void testDuck(Duck duck) {
		duck.quack();
		duck.fly();
	}
}
