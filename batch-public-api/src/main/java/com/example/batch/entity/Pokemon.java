package com.example.batch.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pokemon")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pokemon {

    @Id
    private String name;

    private String url;
}
