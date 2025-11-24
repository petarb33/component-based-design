package com.f1.tickets_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TrackDTO {
    private Integer id;
    private String name;
    private String country;

    @JsonProperty("length_km")
    private BigDecimal lengthKm;

    private Integer turns;
}