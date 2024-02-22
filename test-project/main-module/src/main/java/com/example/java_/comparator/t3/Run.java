package com.example.java_.comparator.t3;

import lombok.ToString;

import java.util.Arrays;
import java.util.Comparator;

public class Run {
	public static void main(String[] args) {

		@ToString
		class User{
			String name;
			int age;

			User(String name, int age){
				this.name = name;
				this.age = age;
			}
		}

		User[] users = {
				new User("홍길동", 32),
				new User("김춘추", 64),
				new User("임꺽정", 48),
				new User("박혁거세", 14),
		};

		Arrays.sort(users, new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				return Integer.compare(o1.age, o2.age);
			}
		});

		System.out.println(Arrays.toString(users));

	}
}
