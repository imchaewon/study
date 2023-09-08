package com.example.spring.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component("prop4")
@PropertySource(value = {"application2.properties", "application3.properties"})
public class C4 implements ApplicationListener<ApplicationStartedEvent> {

	@Value("${test.username}")
	private String username;

	@Value("${test.password}")
	private int password;

	@Value("${app2.asdf}")
	private String aaa;

	@Value("${app3.zxcv}")
	private String bbb;

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		System.out.println("username7: " + username);
		System.out.println("password8: " + password);
		System.out.println("aaa: " + aaa);
		System.out.println("bbb: " + bbb);
	}
}
