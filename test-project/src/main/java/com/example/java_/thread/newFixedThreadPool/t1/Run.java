package com.example.java_.thread.newFixedThreadPool.t1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Run {
	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(10);
		IntStream.range(0, 10).forEach(n -> executor.execute(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(300);
				String threadName = Thread.currentThread().getName();
				System.out.println("hello " + threadName);
				System.out.println("num:" + n);

			} catch (Exception e) {
				System.out.println("Run.main()");
				e.printStackTrace();
			}
		}));

//				쓰레드풀에 쓰레드를 10개를 뛰어놀게 한다.
//				10번을 반복해서 쓰레드풀에 일을 시킨다. (쓰레드풀안의 쓰레드 하나가 선택되어 일처리를 할것이다)
//				execute메소드를 사용했다. 이 메소드는 void 를 리턴한다. 즉 일처리를 시키기만 하지 결과를 보고받지 않을것이다.
//				구현내용은 300밀리초를 기다렸다가 Hello 쓰레드이름을 출력하는 것이다.
//				자바8부터는 for(int i = 0; i < 10; i++) 보다는 저렇게 사용하는게 좋다.

	}
}
