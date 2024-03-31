package com.example.spring.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

//@Component("prop6")
//public class C6 implements ApplicationListener<ApplicationStartedEvent> {
//	private final Environment env;
//
//	@Autowired
//	public C6(Environment env) {
//		this.env = env;
//	}
//
//	@Override
//	public void onApplicationEvent(ApplicationStartedEvent event) {
//		String username = env.getProperty("test.username");
//		String password = env.getProperty("test.password");
//		System.out.println("username1: " + username);
//		System.out.println("password2: " + password);
//	}
//}
