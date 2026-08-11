package com.example.batch.job;

import com.example.batch.client.TourApiClient;
import com.example.batch.dto.TourImageApiResponse;
import com.example.batch.entity.TourSpotImage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TourSpotImageReader implements ItemReader<TourSpotImage> {

    private final JdbcTemplate jdbcTemplate;
    private final TourApiClient tourApiClient;

    private List<String> contentIds = null;
    private int contentIdIndex = 0;
    private List<TourSpotImage> buffer = new ArrayList<>();

    @Override
    public TourSpotImage read() {
        if (contentIds == null) {
            contentIds = jdbcTemplate.queryForList("SELECT content_id FROM tour_spot", String.class);
            log.info("이미지 수집 대상 콘텐츠 수: {}건", contentIds.size());
        }

        while (buffer.isEmpty()) {
            if (contentIdIndex >= contentIds.size()) {
                return null;
            }

            String contentId = contentIds.get(contentIdIndex++);

            if (contentIdIndex % 1000 == 0) {
                log.info("이미지 수집 진행: {}/{}", contentIdIndex, contentIds.size());
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            List<TourImageApiResponse.ImageItem> items = tourApiClient.fetchImages(contentId);
            for (TourImageApiResponse.ImageItem item : items) {
                buffer.add(TourSpotImage.builder()
                        .contentId(item.getContentid())
                        .imgName(item.getImgname())
                        .originImgUrl(item.getOriginimgurl())
                        .smallImageUrl(item.getSmallimageurl())
                        .serialNum(item.getSerialnum())
                        .build());
            }
        }

        return buffer.remove(0);
    }
}
