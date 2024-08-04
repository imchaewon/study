package thread.sync.test;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class SyncTest2Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                counter.count();
            }
        };

        Thread thread1 = new Thread(task, "thread-1");
        Thread thread2 = new Thread(task, "thread-2");

        thread1.start();
        thread2.start();
    }

    static class Counter{
        public void count() {
            int localValue = 0;
            for (int i = 0; i < 1000; i++) {
                localValue = localValue + 1;
            }
            sleep(100);
            log("결과: " + localValue);
        };
    }
}