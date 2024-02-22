package com.example.spring.copyProperties.t1;

import static org.springframework.beans.BeanUtils.copyProperties;

public class Run {
	public static void main(String[] args) {
		Human human = new Human("asd",12,132.4f);

		Clone clone = new Clone();
		copyProperties(human,clone);

		System.out.println(human);
		System.out.println(clone);
	}
}
