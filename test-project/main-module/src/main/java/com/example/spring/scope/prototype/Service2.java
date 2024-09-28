package com.example.spring.scope.prototype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("scopePrototypeService2")
@Getter
@RequiredArgsConstructor
public class Service2 {
	private final Proto<C2> runner;

	static class C2{
	}
}