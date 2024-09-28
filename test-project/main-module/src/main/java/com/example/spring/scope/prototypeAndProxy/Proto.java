package com.example.spring.scope.prototypeAndProxy;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component("scopePrototypeAndProxyProto")
@Scope(scopeName = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Proto<T> {
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