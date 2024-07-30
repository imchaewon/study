package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV1 {
    public static void main(String[] args) {
        log("main start");

        HelloRunnable runnable = new HelloRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        log("main end");
    }

    static class HelloRunnable implements Runnable{
        @Override
        public void run() {
            log("run()");
        }
    }
}