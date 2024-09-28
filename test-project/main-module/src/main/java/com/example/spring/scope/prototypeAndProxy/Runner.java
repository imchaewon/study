package com.example.spring.scope.prototypeAndProxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component("scopePrototypeAndProxyRunner")
public class Runner implements ApplicationRunner {
	@Autowired
	ApplicationContext applicationContext;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Service1 service1_1 = applicationContext.getBean("scopePrototypeAndProxyService1", Service1.class);
		Service1 service1_2 = applicationContext.getBean("scopePrototypeAndProxyService1", Service1.class);
		Service2 service2_1 = applicationContext.getBean("scopePrototypeAndProxyService2", Service2.class);
		Service2 service2_2 = applicationContext.getBean("scopePrototypeAndProxyService2", Service2.class);

		log.info(service1_1.getRunner().toString());
		log.info(service1_2.getRunner().toString());
		log.info(service2_1.getRunner().toString());
		log.info(service2_2.getRunner().toString());
	}
}