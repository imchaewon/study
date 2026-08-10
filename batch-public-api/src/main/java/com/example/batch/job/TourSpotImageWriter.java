package com.example.batch.job;

import com.example.batch.entity.TourSpotImage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class TourSpotImageWriter implements ItemWriter<TourSpotImage> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void write(Chunk<? extends TourSpotImage> chunk) {
        List<? extends TourSpotImage> items = chunk.getItems();
        log.info("이미지 DB 적재: {}건", items.size());

        String placeholders = IntStream.range(0, items.size())
                .mapToObj(i -> "(?, ?, ?, ?, ?)")
                .collect(Collectors.joining(", "));

        String sql = "INSERT INTO tour_spot_image (content_id, img_name, origin_img_url, small_image_url, serial_num) VALUES "
                + placeholders;

        Object[] params = items.stream()
                .flatMap(t -> Stream.of(
                        t.getContentId(), t.getImgName(), t.getOriginImgUrl(),
                        t.getSmallImageUrl(), t.getSerialNum()))
                .toArray();

        jdbcTemplate.update(sql, params);
    }
}
