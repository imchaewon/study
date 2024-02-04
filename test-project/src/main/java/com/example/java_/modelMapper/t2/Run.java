package com.example.java_.modelMapper.t2;

import lombok.*;
import org.modelmapper.ModelMapper;

public class Run {
	public static void main(String[] args) {
		ModelMapper mapper = new ModelMapper();

		mapper.typeMap(C1.class, C2.class).addMappings(mapping -> {
			mapping.map(C1::getAge, C2::setAgee); // 이름 지정해 직접 매핑
			mapping.map(C1::getName, C2::setNamee); // 이름 지정해 직접 매핑
			mapping.skip(C2::setDdd); // 스킵 가능
		});

		C1 c1 = new C1(1, "김김김", 12, 123);
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
		int ddd;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Setter
	@ToString
	private static class C2{
		int id;
		String namee;
		int agee;
		int ddd;
	}
}