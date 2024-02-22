package com.example.java_.modelMapper.t1;

import lombok.*;
import org.modelmapper.ModelMapper;

public class Run {
	public static void main(String[] args) {
		ModelMapper mapper = new ModelMapper();
		C1 c1 = new C1(1, "김김김", 12);
		C2 c2 = mapper.map(c1, C2.class);

		System.out.println("c1 = " + c1);
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