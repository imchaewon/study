package thread.start;

public class DaemonThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": name() start");
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true); // 데몬 스레드 여부
        daemonThread.start();
        System.out.println(Thread.currentThread().getName() + ": name() end");
    }

    static class DaemonThread extends Thread{
        @Override
        public void run() {
            System.out.println("run(): " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("run() end: " + Thread.currentThread().getName());
        }
    }
}