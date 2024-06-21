package com.example.java_.stream.terminal.collect.toMap.t1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class Run {
	public static void main(String[] args) {
		@ToString
		@AllArgsConstructor
		@Getter
		class Person{
			private String name;
			private int age;
		}

		List<Person> people = List.of(
				new Person("Alice", 30),
				new Person("Bob", 25),
				new Person("Charlie", 35)
		);

		// 이름을 키로 하는 ConcurrentMap 생성
		ConcurrentMap<String, Person> nameToPersonMap = people.stream()
				.collect(Collectors.toConcurrentMap(e -> e.getName(), e -> e));

		// 결과 출력
		nameToPersonMap.forEach((name, person) -> System.out.println(name + ": " + person));
	}
}