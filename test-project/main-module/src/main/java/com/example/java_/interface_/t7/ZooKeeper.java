package com.example.java_.interface_.t7;

public class ZooKeeper {
	public static void main(String[] args) {
		ZooKeeper z = new ZooKeeper();
		Tiger t = new Tiger();
		Lion l = new Lion();
		z.feed(t);
		z.feed(l);
		z.feed(new Predator() {
			@Override
			public String getFood() {
				return "melon";
			}
		});
		z.feed(() -> {
			return "strawberry";
		});
	}
	void feed(Predator predator) {
		System.out.println("feed: " + predator.getFood());  // 항상 feed apple 만을 출력한다.
	}
}
