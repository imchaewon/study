package com.example.java_.callback.t1;

public class Callee {
	private Callback callback;
	
	public Callee(Callback callback) {
		this.callback = callback;
	}
	
	public void execute() throws InterruptedException {
		for (int i = 0; i < 5; i++) {
			callback.qrCheck(false);
			Thread.sleep(1000);
			
		}
		
		callback.qrCheck(true);
		
	}
	
}
