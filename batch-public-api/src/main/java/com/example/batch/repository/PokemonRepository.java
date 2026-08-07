package com.example.batch.repository;

import com.example.batch.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, String> {

    @Modifying
    @Query(value = """
            INSERT INTO pokemon (name, url)
            VALUES (:#{#p.name}, :#{#p.url})
            ON DUPLICATE KEY UPDATE
                url = VALUES(url)
            """, nativeQuery = true)
    void upsert(@Param("p") Pokemon pokemon);

    default void upsertAll(List<? extends Pokemon> pokemons) {
        pokemons.forEach(this::upsert);
    }
}
