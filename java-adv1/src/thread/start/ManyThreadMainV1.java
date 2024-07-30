package thread.start;

import static util.MyLogger.log;

public class ManyThreadMainV1 {
    public static void main(String[] args) {
        log("main() start");

        HelloRunnable runnable = new HelloRunnable();

        Thread thread11 = new Thread(runnable);
        thread11.start();

        Thread thread22 = new Thread(runnable);
        thread22.start();

        Thread thread33 = new Thread(runnable);
        thread33.setName("333");
        thread33.start();

        log("main() end");
    }
}