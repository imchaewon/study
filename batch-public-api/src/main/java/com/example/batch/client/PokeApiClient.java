package com.example.batch.client;

import com.example.batch.dto.PokeApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PokeApiClient {

    private final RestTemplate restTemplate;

    @Value("${poke.api.base-url}")
    private String baseUrl;

    @Value("${poke.api.limit}")
    private int limit;

    public List<PokeApiResponse.PokemonItem> fetchPage(int offset) {
        String url = baseUrl + "/pokemon?limit=" + limit + "&offset=" + offset;
        log.info("API 호출: offset={}", offset);

        PokeApiResponse response = restTemplate.getForObject(url, PokeApiResponse.class);
        if (response == null || response.getResults() == null) {
            return Collections.emptyList();
        }
        return response.getResults();
    }

    public int fetchTotalCount() {
        String url = baseUrl + "/pokemon?limit=1";
        PokeApiResponse response = restTemplate.getForObject(url, PokeApiResponse.class);
        if (response == null) {
            return 0;
        }
        log.info("총 데이터 수: {}", response.getCount());
        return response.getCount();
    }
}
