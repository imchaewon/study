package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV4 {
    public static void main(String[] args) throws InterruptedException {
        log("start");
        SomeTask task1 = new SomeTask(1, 50);
        Thread thread1 = new Thread(task1, "thread1");

        thread1.start();

        // 스레드가 종료될 때까지 대기
        log("join() - main 스레드가 thread1, thread2 종료까지 대기");
        thread1.join(1000);
        log("main 스레드 대기 완료");

        log("task1.result = " + task1.result);

        log("end");
    }

    static class SomeTask implements Runnable {
        int startValue;
        int endValue;
        int result;

        public SomeTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            log("작업 시작");
            sleep(2000);
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }
            result = sum;
            log("작업 완료 result = " + result);
        }
    }
}