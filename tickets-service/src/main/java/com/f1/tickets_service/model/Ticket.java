package com.f1.tickets_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotNull(message = "Price can't be null")
    @Column(name = "price", nullable = false, precision = 8, scale = 2)
    private BigDecimal price;

    @NotBlank(message = "Category can't be blank")
    @Size(max = 50)
    @Column(name = "category", length = 50)
    private String category;

    @NotBlank(message = "Sector can't be blank")
    @Size(max = 1, min = 1)
    @Column(name = "sector", length = 10)
    private String sector;

    @NotBlank(message = "Type can't be blank")
    @Lob
    @Column(name = "type", nullable = false)
    private String type;

    @JsonProperty("race_id")
    @Column(name = "race_id")
    @NotNull(message = "Race ID can't be null")
    private Integer raceId;

}