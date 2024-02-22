package com.example.java_.exception_.t0;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class C1 {
	public static void main(String[] args) throws Exception{

//		m2();
		try {
			m2();
			m3();
			m1();
		} catch (FileNotFoundException e) {
			System.out.println("aaaaaa");
//			throw new RuntimeException(e);
		} catch (TwoException e) {
			System.out.println("bbbbbb");
//			throw new RuntimeException(e);
		} catch (Exception e){
			System.out.println("cccccccc");
		}


	}

	private static void m3() {
		System.out.println(1/0);
	}

	private static void m2() throws TwoException {
		for (int i = 0; i < 3; i++) {
			if (i == 2) {
				throw new TwoException("asd");
			}else{
				System.out.println("print: " + i);
			}
		}
	}

	private static void m1() throws FileNotFoundException{
			BufferedReader reader = new BufferedReader(new FileReader("aa"));
	}
}
