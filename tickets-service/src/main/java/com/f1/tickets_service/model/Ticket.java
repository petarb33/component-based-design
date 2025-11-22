package com.f1.tickets_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "price", nullable = false, precision = 8, scale = 2)
    private BigDecimal price;

    @Size(max = 50)
    @Column(name = "category", length = 50)
    private String category;

    @Size(max = 10)
    @Column(name = "sector", length = 10)
    private String sector;

    @NotNull
    @Lob
    @Column(name = "type", nullable = false)
    private String type;

    @JsonProperty("race_weekend_id")
    @Column(name = "race_weekend_id")
    private Integer raceWeekendId;

}