package com.example.batch.job;

import com.example.batch.client.TourApiClient;
import com.example.batch.dto.TourApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TourSpotReader implements ItemReader<TourApiResponse.Item> {

    private final TourApiClient tourApiClient;

    private List<TourApiResponse.Item> buffer = new ArrayList<>();
    private int currentPage = 1;
    private int totalCount = -1;
    private int processedCount = 0;

    @Override
    public TourApiResponse.Item read() {
        if (totalCount == -1) {
            totalCount = tourApiClient.fetchTotalCount();
            log.info("총 데이터 수: {}", totalCount);
        }

        if (buffer.isEmpty()) {
            if (processedCount >= totalCount) {
                return null;
            }
            buffer = new ArrayList<>(tourApiClient.fetchPage(currentPage));
            currentPage++;
        }

        if (buffer.isEmpty()) {
            return null;
        }

        TourApiResponse.Item item = buffer.remove(0);
        processedCount++;
        return item;
    }
}
