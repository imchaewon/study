package thread.collection.simple;

import java.util.ArrayList;
import java.util.List;

public class SimpleListMainV00 {
	public static void main(String[] args) throws InterruptedException {
		List<String> list = new ArrayList<>();

		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			Thread thread = new Thread(() -> {
				list.add("A");
			});
			threads.add(thread);
		}
		for (Thread thread : threads) {
			thread.start();
		}
		for (Thread thread : threads) {
			thread.join();
		}

		// 스레드1, 스레드2가 동시에 실행 가정
		System.out.println(list.size());
	}
}