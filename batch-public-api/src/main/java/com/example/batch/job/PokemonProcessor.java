package com.example.batch.job;

import com.example.batch.dto.PokeApiResponse;
import com.example.batch.entity.Pokemon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PokemonProcessor implements ItemProcessor<PokeApiResponse.PokemonItem, Pokemon> {

    @Override
    public Pokemon process(PokeApiResponse.PokemonItem item) {
        return Pokemon.builder()
                .name(item.getName())
                .url(item.getUrl())
                .build();
    }
}
