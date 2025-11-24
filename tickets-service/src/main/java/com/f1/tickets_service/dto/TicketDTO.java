package com.f1.tickets_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketDTO {
    private Long id;

    @NotNull(message = "Price can't be null")
    private BigDecimal price;

    @NotBlank(message = "Category can't be blank")
    @Size(max = 50)
    private String category;

    @NotBlank(message = "Sector can't be blank")
    @Size(max = 1, min = 1)
    private String sector;

    @NotBlank(message = "Type can't be blank")
    @Size(max = 50)
    private String type;

    @JsonProperty("race_id")
    @NotNull(message = "Race ID can't be null")
    private Integer raceId;
}