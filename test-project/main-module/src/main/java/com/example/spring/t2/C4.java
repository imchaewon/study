package com.example.spring.t2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("t2")
public class C4 {
	public C4 m2(){
		System.out.println("m2m2m2");
		return this;
	}

	public List<Integer> m3(){
		List<Integer> list = Arrays.asList(1,2,3);
		System.out.println(list);
		return list;
	}
}
