package com.example.spring.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component("prop5")
@PropertySource(value = {"application.yml"}, factory = YamlPropertySourceFactory.class)
public class C5 implements ApplicationListener<ApplicationStartedEvent> {

	@Value("${test.aaa}")
	private String aaa;

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		System.out.println("aaa: " + aaa);
	}
}
