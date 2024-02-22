package com.example.java_.gofDesignPatterns.creational.singleton.t1;

public class SSO {
	public static void main(String[] args) {

		Singleton sg1 = Singleton.getInstance();
		sg1.count();

		Singleton sg2 = Singleton.getInstance();
		sg2.count();

		Singleton sg3 = Singleton.getInstance();
		sg3.count();

		System.out.println(sg1.getCount());

	}
}
