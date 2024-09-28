package com.example.spring.scope.prototype;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component("scopePrototypeRunner")
@RequiredArgsConstructor
public class Runner implements ApplicationRunner {
	@Autowired
	ApplicationContext applicationContext;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		com.example.spring.scope.prototype.Service1 service1_1 = applicationContext.getBean("scopePrototypeService1", com.example.spring.scope.prototype.Service1.class);
		com.example.spring.scope.prototype.Service1 service1_2 = applicationContext.getBean("scopePrototypeService1", com.example.spring.scope.prototype.Service1.class);
		com.example.spring.scope.prototype.Service2 service2_1 = applicationContext.getBean("scopePrototypeService2", com.example.spring.scope.prototype.Service2.class);
		com.example.spring.scope.prototype.Service2 service2_2 = applicationContext.getBean("scopePrototypeService2", com.example.spring.scope.prototype.Service2.class);

		log.info(service1_1.getRunner().toString());
		log.info(service1_2.getRunner().toString());
		log.info(service2_1.getRunner().toString());
		log.info(service2_2.getRunner().toString());
	}
}