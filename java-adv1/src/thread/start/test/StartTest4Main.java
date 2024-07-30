package thread.start.test;

import static util.MyLogger.log;

public class StartTest4Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new PrintWork("A", 1000), "Thread-A");
        Thread thread2 = new Thread(new PrintWork("B", 500), "Thread-B");
        thread1.start();
        thread2.start();
    }

    static class PrintWork implements Runnable {
        String word;
        int ms;

        public PrintWork(String word, int ms) {
            this.word = word;
            this.ms = ms;
        }

        @Override
        public void run() {
            while (true) {
                log(word);

                try {
                    Thread.sleep(ms);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}