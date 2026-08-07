package com.example.batch.job;

import com.example.batch.dto.TourApiResponse;
import com.example.batch.entity.TourSpot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TourSpotProcessor implements ItemProcessor<TourApiResponse.Item, TourSpot> {

    @Override
    public TourSpot process(TourApiResponse.Item item) {
        log.info("process start");
        return TourSpot.builder()
            .contentId(item.getContentid())
            .title(item.getTitle())
            .addr1(item.getAddr1())
            .areaCode(item.getAreacode())
            .contentTypeId(item.getContenttypeid())
            .firstImage(item.getFirstimage())
            .mapX(item.getMapx())
            .mapY(item.getMapy())
            .tel(item.getTel())
            .build();
    }
}