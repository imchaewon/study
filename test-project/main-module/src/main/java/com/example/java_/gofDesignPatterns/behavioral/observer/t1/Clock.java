package com.example.java_.gofDesignPatterns.behavioral.observer.t1;

import java.util.ArrayList;
import java.util.List;

public class Clock implements Subject{
	public List<Observer> observerCollection;
	public String time;

	public Clock(String time){
		observerCollection = new ArrayList<>();
		this.time = time;
	}

	@Override
	public void registerObserver(Observer observer) {
		observerCollection.add(observer);
	}

	@Override
	public void unregisterObserver(Observer observer) {
		observerCollection.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observerCollection)
			observer.update();
	}

	public void setTime(String time){
		this.time = time;
		notifyObservers();
	}
}
