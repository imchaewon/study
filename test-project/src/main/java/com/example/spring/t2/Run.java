package com.example.spring.t2;

import com.example.spring.t2.qualifier.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class Run {

	private final C2 c2;
	private final C3 c3;

	@Qualifier("t2")
	private final C4 c4;

	@Qualifier("kiaa")
	private final Car kia;
	private final Car hyundai;

	@RequestMapping("test0")
	void test0(){
		System.out.println(c2.test1());
	}

	@RequestMapping("test1")
	void test1(){
		System.out.println(c3.m1());
	}

	@RequestMapping("test2")
	void test2(){
//		c4.m3();
	}

	@RequestMapping("test3")
	void test3(){
		System.out.println(kia.getCEO());
		System.out.println(hyundai.getCEO());
	}



}
