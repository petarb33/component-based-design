package com.f1.tickets_service.dto;

import com.f1.tickets_service.model.RaceWeekend;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketDTO {
    private Long id;
    private BigDecimal price;
    private String category;
    private String sector;
    private String type;
    @JsonProperty("race_weekend_id")
    private Integer raceWeekendId;
}