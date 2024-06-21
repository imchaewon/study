package com.example.java_.static_.t1;

import lombok.AllArgsConstructor;

public class Run {
	public static void main(String[] args) {
		new C1().m1();
	}
}

class C1{
	void m1(){
		User user1 = TestDataFactory.createUser1();
		User user2 = TestDataFactory.createUser2();
		User user1_later = TestDataFactory.createUser1();

		System.out.println("user1 == user1_later = " + (user1 == user1_later));
		System.out.println("user1.equals(user1_later) = " + (user1.equals(user1_later)));
	}
}

@AllArgsConstructor
class User{
	Long id;
	String name;
}

class TestDataFactory {
	public static User createUser1(){
		return new User(1L, "김준수");
	}
	public static User createUser2(){
		return new User(2L, "김나연");
	}
}