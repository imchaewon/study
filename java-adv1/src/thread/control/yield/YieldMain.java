package thread.control.yield;

import thread.start.HelloRunnable;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class YieldMain{
    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
        long end = System.currentTimeMillis();
        System.out.println("end - start = " + (end - start));
    }

    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
                // 1. empty
//                sleep(1); // 2. sleep
                 Thread.yield(); // 3. yield
            }
        }
    }
}