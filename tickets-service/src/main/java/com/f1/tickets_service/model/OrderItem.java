package com.f1.tickets_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonProperty("order_id")
    //@JsonIgnore
    private Order order;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "ticket_id", nullable = false)
    @JsonProperty("ticket_id")
    //@JsonIgnore
    private Ticket ticket;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

}