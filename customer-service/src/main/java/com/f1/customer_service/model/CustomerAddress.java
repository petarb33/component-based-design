package com.f1.customer_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "customer_address")
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ColumnDefault("'other'")
    @Lob
    @Column(name = "type")
    private String type;

    @Size(max = 255)
    @NotBlank
    @Column(name = "street", nullable = false)
    private String street;

    @Size(max = 100)
    @NotBlank
    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Size(max = 20)
    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Size(max = 100)
    @NotBlank
    @Column(name = "country", nullable = false, length = 100)
    private String country;

}