package com.f1.tickets_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDTO {
    private Long id;

    @NotNull(message = "Customer ID can't be null")
    private Integer customer_id;

    @NotNull(message = "Order date can't be null")
    private LocalDate order_date;

    @NotNull(message = "Ticket status can't be null")
    private TicketStatus status;
}

enum TicketStatus {
    PENDING, CONFIRMED, CANCELLED
}