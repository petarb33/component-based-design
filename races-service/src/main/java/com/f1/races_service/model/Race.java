package com.f1.races_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "races")
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 50)
    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "season")
    private Integer season;

    @ManyToOne()
    @JoinColumn(name = "track_id")
    private Track track;

}