package com.example.batch.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tour_spot_image")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourSpotImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content_id")
    private String contentId;

    @Column(name = "img_name")
    private String imgName;

    @Column(name = "origin_img_url", length = 500)
    private String originImgUrl;

    @Column(name = "small_image_url", length = 500)
    private String smallImageUrl;

    @Column(name = "serial_num")
    private String serialNum;
}
