package com.example.spring.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//@Component("prop2")
//public class C2 implements ApplicationListener<ApplicationStartedEvent> {
//	@Value("${test.username}")
//	private String username;
//
//	@Value("${test.password}")
//	private int password;
//
//	@Value("${test.test2.aaa}")
//	private String aaa;
//
//	@Override
//	public void onApplicationEvent(ApplicationStartedEvent event) {
//		System.out.println("username3: " + username);
//		System.out.println("password4: " + password);
//		System.out.println("aaaaa: " + aaa);
//	}
//}
