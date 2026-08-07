package com.example.batch.job;

import com.example.batch.entity.Pokemon;
import com.example.batch.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PokemonWriter implements ItemWriter<Pokemon> {

    private final PokemonRepository pokemonRepository;

    @Override
    public void write(Chunk<? extends Pokemon> chunk) {
        log.info("DB 적재: {}건", chunk.size());
        pokemonRepository.saveAll(chunk.getItems());
    }
}
