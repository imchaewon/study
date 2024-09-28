package com.example.spring.scope.prototype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("scopePrototypeService1")
@Getter
@RequiredArgsConstructor
public class Service1{
	private final Proto<C1> runner;

	static class C1{
	}
}