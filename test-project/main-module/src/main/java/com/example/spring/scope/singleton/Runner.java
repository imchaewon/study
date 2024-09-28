package com.example.spring.scope.singleton;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component("scopeSingletonRunner")
@RequiredArgsConstructor
public class Runner implements ApplicationRunner {
	public final Service1 service1;
	public final Service2 service2;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info(service1.getRunner().toString());
		log.info(service2.getRunner().toString());
	}
}