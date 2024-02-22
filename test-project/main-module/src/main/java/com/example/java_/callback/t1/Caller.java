package com.example.java_.callback.t1;

public class Caller {
	public static void main(String[] args) throws InterruptedException {
		
//		Callback callback = (check) -> {
//			if (check) {
//				System.out.println("큐알체크됨");
//			}else {
//				System.out.println("기달");
//			}
//		};

		Callback callback = new Callback() {

			@Override
			public void qrCheck(boolean check) {
				if (check) {
					System.out.println("큐알체크됨");
				}else {
					System.out.println("기달");
				}
			}
			
		};
		
		Callee callee = new Callee(callback);
		
		callee.execute();
		
		
	}
}
