package com.example.batch.client;

import com.example.batch.dto.TourApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TourApiClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${tour.api.service-key}")
    private String serviceKey;

    @Value("${tour.api.base-url}")
    private String baseUrl;

    @Value("${tour.api.num-of-rows}")
    private int numOfRows;

    public List<TourApiResponse.Item> fetchPage(int pageNo) {
        String url = baseUrl + "/areaBasedSyncList2"
                + "?serviceKey=" + serviceKey
                + "&pageNo=" + pageNo
                + "&numOfRows=" + numOfRows
                + "&MobileOS=ETC&MobileApp=BatchTest&_type=json&arrange=A";

        log.info("API 호출: page={}", pageNo);
        String raw = restTemplate.getForObject(URI.create(url), String.class);

        try {
            TourApiResponse response = objectMapper.readValue(raw, TourApiResponse.class);
            if (response == null
                    || response.getResponse() == null
                    || response.getResponse().getBody() == null
                    || response.getResponse().getBody().getItems() == null
                    || response.getResponse().getBody().getItems().getItem() == null) {
                return Collections.emptyList();
            }
            return response.getResponse().getBody().getItems().getItem();
        } catch (Exception e) {
            log.error("응답 파싱 실패: {}", raw, e);
            return Collections.emptyList();
        }
    }

    public int fetchTotalCount() {
        String url = baseUrl + "/areaBasedSyncList2"
                + "?serviceKey=" + serviceKey
                + "&pageNo=1&numOfRows=1&MobileOS=ETC&MobileApp=BatchTest&_type=json";

        String raw = restTemplate.getForObject(URI.create(url), String.class);

        try {
            TourApiResponse response = objectMapper.readValue(raw, TourApiResponse.class);
            if (response == null || response.getResponse() == null || response.getResponse().getBody() == null) {
                return 0;
            }
            return response.getResponse().getBody().getTotalCount();
        } catch (Exception e) {
            log.error("totalCount 응답 파싱 실패", e);
            return 0;
        }
    }
}
