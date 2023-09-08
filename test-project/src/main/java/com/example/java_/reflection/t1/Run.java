package com.example.java_.reflection.t1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Run {
	public static void main(String[] args) {

		// 객체 생성(패키지명까지 쓰는 방식) & 생성자 찾기
		Class<?> clazz = null;
		try {
			clazz = Class.forName("com.example.java_.reflection.t1.Person");
			Constructor<?> constructor = clazz.getDeclaredConstructor();

			System.out.println(constructor);

		} catch (ClassNotFoundException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}


		// 객체 생성(클래스명만 바로 쓰는 방식) & 메소드 찾기 & 메소드 실행
		Class<Person> clazz2 = Person.class;
		Method[] methodList = clazz2.getDeclaredMethods();
		try {
			System.out.println(methodList[0].invoke(clazz2.newInstance())); // 27이 출력됨
		} catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
			throw new RuntimeException(e);
		}


		// 필드 불러오기 & 필드값 변경
		Field[] fields = clazz.getDeclaredFields();
		System.out.println("field2: " + Arrays.toString(fields));
		System.out.println("field2[0].getName(): " + fields[0].getName());

		Person person = new Person();
		try {
			fields[1].set(person, 17);
			System.out.println(fields[1].get(person));  // 17이 출력됨
			for (Field field1 : fields) {
				System.out.printf("%s: %s\r\n", field1.getName(), field1.get(person));
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}


	}
}
