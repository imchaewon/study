package com.example.java_.gofDesignPatterns.behavioral.observer.t1;

public interface Subject {
	void registerObserver(Observer observer);
	void unregisterObserver(Observer observer);
	void notifyObservers();
}
