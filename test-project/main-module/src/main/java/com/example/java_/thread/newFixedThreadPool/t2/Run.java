package com.example.java_.thread.newFixedThreadPool.t2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Run {
	public static void main(String[] args) {

		ExecutorService executor0 = Executors.newSingleThreadExecutor();
		IntStream.rangeClosed(1, 10).forEach(n -> executor0.execute(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
				String threadName = Thread.currentThread().getName();
				System.out.println("num:" + n);
			} catch (Exception e) {
				System.out.println("Run.main()");
				e.printStackTrace();
			}
		}));

		ExecutorService executor = Executors.newFixedThreadPool(2);
		final List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
		Future<Integer> future = executor.submit(() -> {
			TimeUnit.MILLISECONDS.sleep(5000);
			int result = integers.stream().mapToInt(i -> i.intValue()).sum();
			return result;
		});

		try {
			Integer result = future.get();
			System.out.println("result: " + result);
			executor.shutdownNow();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

//		쓰레드풀에 쓰레드를 10개를 뛰어놀게 한다.
//		쓰레드풀에 하나의 일을 시킨다. (쓰레드풀안의 쓰레드 하나가 선택되어 일처리를 할것이다)
//		submit메소드를 사용했다. 이 메소드는 future 를 리턴한다. 즉 일처리를 시키기고, 그에 따른 결과를 보고받을 것이다.
//		구현내용은 5000밀리초를 기다렸다가 리스트안의 숫자들의 합을 리턴한다.
//		리턴 받은 future 로 부터 값을 얻는다. 여기서 get()메소드는 블럭된다. (타임아웃을 매개변수로 넣을 수도 있다) 아마 일처리를 하는 쪽의 쓰레드에서 일을 다 끝내고 set() 같은 것을 해 줄 때가지 블럭될거 같다.

	}
}
