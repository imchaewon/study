package com.example.java_.thread.join.t2;

public class Run {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					System.out.println("Thread 1 is completed.");
				} catch (Exception e) {
					System.out.println("Run.main()");
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				Thread.sleep(2000);
				System.out.println("Thread 2 is completed.");
			} catch (Exception e) {
				System.out.println("Run.main()");
				e.printStackTrace();
			}
		});

		t1.start();
		t2.start();

		System.out.println("주 스레드가 조인 없이 완료되었음.");
		
		try {
			t1.join();
			t2.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Run.main()");
		}
		System.out.println("주 스레드가 조인을 사용하여 완료되었음.");

	}
}














