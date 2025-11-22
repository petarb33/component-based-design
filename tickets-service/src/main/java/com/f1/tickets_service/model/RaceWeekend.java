package com.f1.tickets_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "race_weekend")
public class RaceWeekend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Size(max = 100)
    @Column(name = "circuit_name", length = 100)
    @JsonProperty("circuit_name")
    private String circuitName;

    @Size(max = 50)
    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "season")
    private Integer season;

    @ColumnDefault("'main'")
    @Lob
    @Column(name = "weekend_type")
    @JsonProperty("weekend_type")
    private String weekendType;
}