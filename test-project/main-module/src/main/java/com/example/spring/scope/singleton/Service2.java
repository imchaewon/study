package com.example.spring.scope.singleton;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("scopeSingletonService2")
@Getter
@RequiredArgsConstructor
public class Service2 {
	private final Single<C2> runner;

	static class C2{
	}
}