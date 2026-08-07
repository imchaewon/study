package com.example.batch.repository;

import com.example.batch.entity.TourSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourSpotRepository extends JpaRepository<TourSpot, String> {

    @Modifying
    @Query(value = """
            INSERT INTO tour_spot (content_id, title, addr1, area_code, content_type_id, first_image, mapx, mapy, tel)
            VALUES (:#{#t.contentId}, :#{#t.title}, :#{#t.addr1}, :#{#t.areaCode}, :#{#t.contentTypeId}, :#{#t.firstImage}, :#{#t.mapX}, :#{#t.mapY}, :#{#t.tel})
            ON DUPLICATE KEY UPDATE
                title = VALUES(title),
                addr1 = VALUES(addr1),
                area_code = VALUES(area_code),
                content_type_id = VALUES(content_type_id),
                first_image = VALUES(first_image),
                mapx = VALUES(mapx),
                mapy = VALUES(mapy),
                tel = VALUES(tel)
            """, nativeQuery = true)
    void upsert(@Param("t") TourSpot tourSpot);

    default void upsertAll(List<? extends TourSpot> tourSpots) {
        tourSpots.forEach(this::upsert);
    }
}
