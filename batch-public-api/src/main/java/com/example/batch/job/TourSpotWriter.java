package com.example.batch.job;

import com.example.batch.entity.TourSpot;
import com.example.batch.repository.TourSpotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TourSpotWriter implements ItemWriter<TourSpot> {

    private final TourSpotRepository tourSpotRepository;

    @Override
    public void write(Chunk<? extends TourSpot> chunk) {
        log.info("DB 적재: {}건", chunk.size());
        tourSpotRepository.saveAll(chunk.getItems());
    }
}
