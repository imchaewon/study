package com.example.java_.modelMapper.t5;

import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 인수로 null 을 넣은 경우
public class Run {
	public static void main(String[] args) {
		ModelMapper mapper = new ModelMapper();

		C1 c1 = new C1(1, "aa", 12);

		C2 c2 = null;

		try {
			c2 = mapper.map(null, C2.class);
		} catch (Exception e) {
			System.out.println("첫 번째 인수 null 들어감");
			e.printStackTrace();
		}

		try {
			c2 = mapper.map(c1, null);
		} catch (Exception e) {
			System.out.println("두 번째 인수 null 들어감");
			e.printStackTrace();
		}

		System.out.println("c2 = " + c2);
	}

	@AllArgsConstructor
	@Getter
	@ToString
	private static class C1{
		int id;
		String name;
		int age;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Setter
	@ToString
	private static class C2{
		int id;
		String name;
		int age;
	}
}