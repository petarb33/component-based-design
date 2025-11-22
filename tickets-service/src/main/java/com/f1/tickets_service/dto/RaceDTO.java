package com.f1.races_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RaceDTO {
    private Integer id;
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Country is required")
    private String country;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Season cannot be null")
    @Min(2025)
    private Integer season;

    @NotNull(message = "Track ID is required")
    @Min(1)
    @JsonProperty("track_id")
    private Integer trackId;

}
