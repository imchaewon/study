package com.example.java_.gofDesignPatterns.behavioral.observer.t1;

public class Run {
	public static void main(String[] args) {
		Clock c = new Clock("8");
		Doctor doctor = new Doctor(c);
		Developer developer = new Developer(c);
		Nerd nerd = new Nerd(c);

		{
			System.out.println(doctor.state);
			System.out.println(developer.state);
			System.out.println(nerd.state);
		}

		{
			c.setTime("9");
		}

		{
			System.out.println(doctor.state);
			System.out.println(developer.state);
			System.out.println(nerd.state);
		}
	}
}
