package com.example.java_.annotation.springframework.nonNull.t1;

public class Run {
	public static void main(String[] args) {

		C1 c1 = new C1();

		int random = (int)(Math.random() * 2);
		String gender;
		if(random == 1){
			gender = null;
		}else{
			gender = "m";
		}
		c1.gender = gender;

		System.out.println(gender);
	}
}
