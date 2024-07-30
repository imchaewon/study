package thread.start;

public class BadThreadMain {
    public static void main(String[] args) {
        System.out.println("thread start: " + Thread.currentThread().getName());

        HelloThread helloThread = new HelloThread();
        System.out.println("start() 호출 전: " + Thread.currentThread().getName());
        helloThread.run();
        System.out.println("start() 호출 전: " + Thread.currentThread().getName());

        System.out.println("thread end: " + Thread.currentThread().getName());
    }
}