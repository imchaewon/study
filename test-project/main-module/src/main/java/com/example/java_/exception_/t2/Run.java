package com.example.java_.exception_.t2;

public class Run {
	public static void main(String[] args) {

		Account kimAccount = new Account("김미진", 100);
		try {
			kimAccount.withdraw(150);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println(kimAccount.current);
		try {
			
		} catch (Exception e) {
			System.out.println("Run.main()");
			e.printStackTrace();
		}

	}
}
