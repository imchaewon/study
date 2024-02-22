package com.example.java_.class_.t7;

public class Run {
	public static void main(String[] args) {

		Student original = new Student("20170328", "김자바", 22, "경영학과", new int[] {100,90,80});

		Student cloned = original.getStudent();
		original.age = 24;
		original.scores[0] = 0;

		System.out.println("복제한 Student 객체의 필드값");
		System.out.println("sno: " + cloned.sno);
		System.out.println("name: " + cloned.name);
		System.out.println("age: " + cloned.age);
		System.out.println("age: " + cloned.age);
		System.out.println("dept: " + cloned.dept);
		System.out.println("scores[0]: " + cloned.scores[0]);
		System.out.println();

		System.out.println("원본 Student 객체의 필드값");
		System.out.println("sno: " + original.sno);
		System.out.println("name: " + original.name);
		System.out.println("age: " + original.age);
		System.out.println("age: " + original.age);
		System.out.println("dept: " + original.dept);
		System.out.println("scores[0]: " + original.scores[0]);

	}
}
