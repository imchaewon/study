package com.example.java_.interface_.t3;

public class Run {
	public static void main(String[] args) {
//			StepListener stepListener = () -> System.out.println("step2 true");
		
		StepListener stepListener = new StepListener() {		//	익명 객체 : 인터페이스 객체를 생성하면서 구현하기
																//	함수형 인터페이스 : 인터페이스에서 메소드가 1개인거 
																//	람다식 : 함수형 인터페이스를 줄여서 쓰는
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("step2 true");
			}
		};
		
		boolean isTrue = step1();
		if (isTrue) {
			System.out.println("step1 true");
		}
		step2(stepListener);
		step3();
	}
	
	public static boolean step1() {
		System.out.println("step1");
		return true;
	}

	public static void step2(StepListener stepListener) {
		new Thread() {
			@Override
			public void run() {
				stepListener.run();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
			}
		}.start();
	}

	public static void step3() {
		System.out.println("step3");
	}
	
	public interface StepListener {
		//	Step이 실행되고난 후 실행이 된
		void run();
	}
}
