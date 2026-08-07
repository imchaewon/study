package com.example.batch.job;

import com.example.batch.entity.TourSpot;
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
public class TourSpotWriter implements ItemWriter<TourSpot> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void write(Chunk<? extends TourSpot> chunk) {
        List<? extends TourSpot> items = chunk.getItems();
        log.info("DB 적재: {}건", items.size());

        String placeholders = IntStream.range(0, items.size())
                .mapToObj(i -> "(?, ?, ?, ?, ?, ?, ?, ?, ?)")
                .collect(Collectors.joining(", "));

        String sql = "INSERT INTO tour_spot (content_id, title, addr1, area_code, content_type_id, first_image, mapx, mapy, tel) VALUES "
                + placeholders
                + " ON DUPLICATE KEY UPDATE"
                + " title=VALUES(title), addr1=VALUES(addr1), area_code=VALUES(area_code),"
                + " content_type_id=VALUES(content_type_id), first_image=VALUES(first_image),"
                + " mapx=VALUES(mapx), mapy=VALUES(mapy), tel=VALUES(tel)";

        Object[] params = items.stream()
                .flatMap(t -> Stream.of(
                        t.getContentId(), t.getTitle(), t.getAddr1(), t.getAreaCode(),
                        t.getContentTypeId(), t.getFirstImage(), t.getMapX(), t.getMapY(), t.getTel()))
                .toArray();

        jdbcTemplate.update(sql, params);
    }
}
