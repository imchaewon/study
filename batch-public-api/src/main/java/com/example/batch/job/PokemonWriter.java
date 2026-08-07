package com.example.batch.job;

import com.example.batch.entity.Pokemon;
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
public class PokemonWriter implements ItemWriter<Pokemon> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void write(Chunk<? extends Pokemon> chunk) {
        List<? extends Pokemon> items = chunk.getItems();
        log.info("DB 적재: {}건", items.size());

        String placeholders = IntStream.range(0, items.size())
                .mapToObj(i -> "(?, ?)")
                .collect(Collectors.joining(", "));

        String sql = "INSERT INTO pokemon (name, url) VALUES " + placeholders
                + " ON DUPLICATE KEY UPDATE url = VALUES(url)";

        Object[] params = items.stream()
                .flatMap(p -> Stream.of(p.getName(), p.getUrl()))
                .toArray();

        jdbcTemplate.update(sql, params);
    }
}
