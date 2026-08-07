package com.example.batch.job;

import com.example.batch.client.PokeApiClient;
import com.example.batch.dto.PokeApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PokemonReader implements ItemReader<PokeApiResponse.PokemonItem> {

    private final PokeApiClient pokeApiClient;

    @Value("${poke.api.limit}")
    private int limit;

    private List<PokeApiResponse.PokemonItem> buffer = new ArrayList<>();
    private int currentOffset = 0;
    private int totalCount = -1;
    private int processedCount = 0;

    @Override
    public PokeApiResponse.PokemonItem read() {
        if (totalCount == -1) {
            totalCount = pokeApiClient.fetchTotalCount();
        }

        if (buffer.isEmpty()) {
            if (processedCount >= totalCount) {
                return null;
            }
            buffer = new ArrayList<>(pokeApiClient.fetchPage(currentOffset));
            currentOffset += limit;
        }

        if (buffer.isEmpty()) {
            return null;
        }

        PokeApiResponse.PokemonItem item = buffer.remove(0);
        processedCount++;
        return item;
    }
}
