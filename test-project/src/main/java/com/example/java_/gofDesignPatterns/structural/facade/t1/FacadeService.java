package com.example.java_.gofDesignPatterns.structural.facade.t1;

public class FacadeService {
	private final SubSystem1 subSystem1;
	private final SubSystem2 subSystem2;
	private final SubSystem3 subSystem3;

	public FacadeService() {
		subSystem1 = new SubSystem1();
		subSystem2 = new SubSystem2();
		subSystem3 = new SubSystem3();
	}

	public void operate(final String name) {
		subSystem1.doSomething(name);
		subSystem2.doSomething(name);
		subSystem3.doSomething(name);
	}
}
