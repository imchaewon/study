package com.example.java_.gofDesignPatterns.behavioral.observer.t1;

public class Developer implements Observer {
	public Clock clock;
	public String state = "보통";

	public Developer(Clock clock){
		this.clock = clock;
		this.clock.registerObserver(this);
	}

	@Override
	public void update() {
		if(clock.time.equals("9"))
			state = "퇴근";
	}
}
