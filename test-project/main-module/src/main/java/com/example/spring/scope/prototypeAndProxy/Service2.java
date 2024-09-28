package com.example.spring.scope.prototypeAndProxy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("scopePrototypeAndProxyService2")
@Getter
@RequiredArgsConstructor
public class Service2 {
	private final Proto<C2> runner;

	static class C2{
	}
}