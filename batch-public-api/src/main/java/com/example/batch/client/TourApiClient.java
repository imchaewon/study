package com.example.batch.client;

import com.example.batch.dto.TourApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TourApiClient {

    private final RestTemplate restTemplate;

    @Value("${tour.api.service-key}")
    private String serviceKey;

    @Value("${tour.api.base-url}")
    private String baseUrl;

    @Value("${tour.api.num-of-rows}")
    private int numOfRows;

    public List<TourApiResponse.Item> fetchPage(int pageNo) {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + "/areaBasedList1")
            .queryParam("serviceKey", serviceKey)
            .queryParam("pageNo", pageNo)
            .queryParam("numOfRows", numOfRows)
            .queryParam("MobileOS", "ETC")
            .queryParam("MobileApp", "BatchTest")
            .queryParam("_type", "json")
            .queryParam("listYN", "Y")
            .queryParam("arrange", "A")
            .build(true)
            .toUri();

        log.info("API 호출: page={}", pageNo);
        TourApiResponse response = restTemplate.getForObject(uri, TourApiResponse.class);

        if (response == null
            || response.getResponse() == null
            || response.getResponse().getBody() == null
            || response.getResponse().getBody().getItems() == null
            || response.getResponse().getBody().getItems().getItem() == null) {
            return Collections.emptyList();
        }

        return response.getResponse().getBody().getItems().getItem();
    }

    public int fetchTotalCount() {
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl + "/areaBasedList1")
            .queryParam("serviceKey", serviceKey)
            .queryParam("pageNo", 1)
            .queryParam("numOfRows", 1)
            .queryParam("MobileOS", "ETC")
            .queryParam("MobileApp", "BatchTest")
            .queryParam("_type", "json")
            .queryParam("listYN", "Y")
            .build(true)
            .toUri();

        TourApiResponse response = restTemplate.getForObject(uri, TourApiResponse.class);

        if (response == null || response.getResponse() == null || response.getResponse().getBody() == null) {
            return 0;
        }

        return response.getResponse().getBody().getTotalCount();
    }
}
