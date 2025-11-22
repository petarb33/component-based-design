package com.f1.customer_service.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class OrderDTO {
    private Integer id;
    private String status;
    private Integer customer_id;
    private LocalDate order_date;

}
