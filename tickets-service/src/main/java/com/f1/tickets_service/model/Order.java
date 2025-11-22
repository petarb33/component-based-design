package com.f1.tickets_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    //@ManyToOne(optional = false)
    //@JoinColumn(name = "customer_id", nullable = false)
    @Column(name = "customer_id")
    @JsonProperty("customer_id")
    //@JsonIgnore
    private Integer customerId;

    @NotNull
    @Column(name = "order_date", nullable = false)
    @JsonProperty("order_date")
    private LocalDate orderDate;

    @ColumnDefault("'pending'")
    @Lob
    @Column(name = "status")
    private String status;
    
}