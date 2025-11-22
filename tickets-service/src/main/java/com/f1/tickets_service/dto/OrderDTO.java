package com.f1.tickets_service.dto;

import com.f1.tickets_service.model.Ticket;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDTO {
    private Long id;
    private Integer customer_id;
    private LocalDate order_date;
    private TicketStatus status;
}

enum TicketStatus {
    PENDING, CONFIRMED, CANCELLED
}