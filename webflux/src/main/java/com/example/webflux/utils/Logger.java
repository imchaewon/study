package com.example.webflux.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Logger {
	public static void doOnNext(Object o) {
		log.info("# doOnNext: "+o);
	}

	public static void onNext(Object o) {
		log.info("# onNext: "+o);
	}

	public static void doOnRequest(Object o) {
		log.info("# doOnRequest: "+o);
	}

	public static void doOnError(Object o) {
		log.info("# doOnError: "+o);
	}
}