package com.example.java_.z.코테.코테3;

public class Main {
	public static void main(String[] args) {
		Elevator elevator = new Elevator();

		// 엘리베이터 이용자 생성
		User user1 = new User(1, 1, 5, elevator);
		User user2 = new User(2, 2, 3, elevator);
		User user3 = new User(3, 3, 2, elevator);

		// 이용자 스레드 실행
		Thread thread1 = new Thread(user1);
		Thread thread2 = new Thread(user2);
		Thread thread3 = new Thread(user3);

		thread1.start();
		thread2.start();
		thread3.start();
	}
}