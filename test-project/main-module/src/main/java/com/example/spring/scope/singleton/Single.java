package com.example.spring.scope.singleton;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component("scopeSingletonSingle")
public class Single<T> {
	private final Map<String, Sinks.Many<T>> sinkMap = new ConcurrentHashMap<>();

	public Map<String, Sinks.Many<T>> getSinks(){
		return sinkMap;
	}

	public Sinks.Many<T> getSink(String key) {
		Sinks.Many<T> sink = sinkMap.get(key);
		return sink;
	}

	public void addSink(String key, Sinks.Many<T> sink) {
		sinkMap.put(key, sink);
	}

	public Sinks.Many<T> removeSink(String key) {
		Sinks.Many<T> removedSink = sinkMap.remove(key);
		return removedSink;
	}
}