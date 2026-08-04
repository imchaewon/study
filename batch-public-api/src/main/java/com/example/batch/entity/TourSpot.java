package com.example.batch.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tour_spot")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourSpot {

    @Id
    private String contentId;

    private String title;
    private String addr1;
    private String areaCode;
    private String contentTypeId;
    private String firstImage;
    private String mapX;
    private String mapY;
    private String tel;
}
