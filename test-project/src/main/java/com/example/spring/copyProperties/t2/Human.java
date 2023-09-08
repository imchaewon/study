package com.example.spring.copyProperties.t2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import static org.springframework.beans.BeanUtils.copyProperties;


@ToString
@Getter
@AllArgsConstructor
public class Human{
	String name;
	int age;
	float weight;

	public Clone doClone(){
		Clone clone = new Clone();
		copyProperties(this,clone);
		return clone;
	}

}
