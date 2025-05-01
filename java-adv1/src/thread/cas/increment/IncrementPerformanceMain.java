package thread.cas.increment;

import java.util.ArrayList;
import java.util.List;

import static util.ThreadUtils.sleep;

public class IncrementPerformanceMain {
	private static final int COUNT = 100_000_000;

	public static void main(String[] args) throws InterruptedException {
		test(new BasicInteger());
		test(new VolatileBasicInteger());
		test(new SyncBasicInteger());
		test(new MyAtomicInteger());
	}

	private static void test(IncrementInteger incrementInteger) {
		long startMs = System.currentTimeMillis();
		for (long i = 0; i < COUNT; i++) {
			incrementInteger.increment();
		}
		long endMs = System.currentTimeMillis();
		System.out.println(incrementInteger.getClass().getSimpleName() + ": ms=" + (endMs - startMs));
	}
}