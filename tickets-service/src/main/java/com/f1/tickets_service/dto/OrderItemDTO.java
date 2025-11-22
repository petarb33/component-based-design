package com.f1.tickets_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    @JsonProperty("order_id")
    private Integer order_id;
    @JsonProperty("ticket_id")
    private Integer ticket_id;
    private Integer quantity;
    private BigDecimal total_price;
}
