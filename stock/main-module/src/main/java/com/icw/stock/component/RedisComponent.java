package com.icw.stock.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisComponent {
	private final RedisTemplate<String,String> redisTemplate;
	private final ObjectMapper objectMapper;

	public void setData(String key, String value){
		if(value == null) {
			log.error(key + ": value is null");
			return;
		}
		redisTemplate.opsForValue().set(key, value);
		log.info("Data saved successfully!");
	}

	public String getData(String key){
		String value = redisTemplate.opsForValue().get(key);
		if(value != null)
			log.info("Data retrieved successfully: " + value);
		else
			log.info("No data found for key: " + key);
		return value;
	}

	public <T> T getAndMapToDto(String key, Class<T> clazz) {
		T obj = null;
		String json = getData(key);
		try {
			obj = objectMapper.readValue(json, clazz);
		} catch (JsonProcessingException e) {
			System.out.println("RedisComponent.getAndMapToDto()");
			e.printStackTrace();
		}
		return obj;
	}
}
