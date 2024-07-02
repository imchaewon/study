package com.example.java_.z;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class 걍테스트4 {
	public static void main(String[] args){
		C1 c1 = C1.builder().name("c1").build();
		for (int i = 0; i < 10000000; i++){
			c1.c2s.add(new C1.C2("a" + i));
		}

		long start = System.currentTimeMillis();
		List<String> list = new ArrayList<>();
		for (C1.C2 c2 : c1.c2s) {
			String name = c1.name;
			list.add(name);
		}
		long end = System.currentTimeMillis();
		System.out.println("result1 = " + (end - start));

		long start2 = System.currentTimeMillis();
		List<String> list2 = new ArrayList<>();
		String name = c1.name;
		for (C1.C2 c2 : c1.c2s) {
			list2.add(name);
		}
		long end2 = System.currentTimeMillis();
		System.out.println("result2 = " + (end2 - start2));

		System.out.println("9999 * 9999 * 9999 = " + 9999 * 9999 * 9999);
		System.out.println("9999 * 9999 * 9999 = " + (long) 9999 * 9999 * 9999);
		System.out.println("9999 * 9999 * 9999 = " + 9999L * 9999 * 9999);
	}

	@Data
	@AllArgsConstructor
	@Builder
	static class C1{
		private String name;
		@Builder.Default
		private List<C2> c2s = new ArrayList<>();

		@AllArgsConstructor
		static class C2{
			private String name;
		}
	}
}