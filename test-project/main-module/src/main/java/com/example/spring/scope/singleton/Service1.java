package com.example.spring.scope.singleton;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("scopeSingletonService1")
@Getter
@RequiredArgsConstructor
public class Service1{
	private final Single<C1> runner;

	static class C1{
	}
}