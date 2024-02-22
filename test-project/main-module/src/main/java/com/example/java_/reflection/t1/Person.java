package com.example.java_.reflection.t1;

class Person {
	String gender;
	int age;

	Person() {
		this.age = 27;
	}

	Person(int age) {
		this.age = age;
	}

	int getAge() {
		return this.age;
	}
}
