package com.example.java_.exception_.t2;

public class Account {

	String name;
	int current;

	public Account(String name, int current) {
		super();
		this.name = name;
		this.current = current;
	}

	void withdraw(int money) throws BadBankingException{
		if (money > current) {
			throw new BadBankingException("잔액이 부족합니다");
		} else {
			current = current - money;
		}
	}

}
