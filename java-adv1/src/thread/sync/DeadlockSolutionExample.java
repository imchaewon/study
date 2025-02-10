package thread.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockSolutionExample {
	private static final Lock lock1 = new ReentrantLock();
	private static final Lock lock2 = new ReentrantLock();

	public static void main(String[] args) {
		Thread thread1 = new Thread(() -> accessResource("Thread-1", lock1, lock2));
		Thread thread2 = new Thread(() -> accessResource("Thread-2", lock2, lock1));

		thread1.start();
		thread2.start();
	}

	private static void accessResource(String threadName, Lock firstLock, Lock secondLock) {
		System.out.println(threadName + " trying to acquire 1st " + firstLock);
		firstLock.lock(); // 첫 번째 락 획득
		System.out.println(threadName + " acquired " + firstLock);

		boolean secondLockAcquired = false;
		try {
			// 잠시 대기(데드락을 쉽게 발생시키기 위해)
			Thread.sleep(50);

			System.out.println(threadName + " trying to acquire 2nd " + secondLock);
			secondLockAcquired = secondLock.tryLock(); // 두 번째 락 획득 시도
			if (secondLockAcquired) {
				System.out.println(threadName + " acquired " + secondLock);
			} else {
				System.out.println(threadName + " could not acquire " + secondLock);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} finally {
			firstLock.unlock();
			System.out.println(threadName + " released " + firstLock);

			if (secondLockAcquired) {
				secondLock.unlock();
				System.out.println(threadName + " released " + secondLock);
			}
		}
	}
}