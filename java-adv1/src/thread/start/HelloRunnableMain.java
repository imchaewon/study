package thread.start;

public class HelloRunnableMain{
    public static void main(String[] args) {
        System.out.println("main(): " + Thread.currentThread().getName());

        HelloRunnable runnable = new HelloRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("main(): " + Thread.currentThread().getName());
    }
}