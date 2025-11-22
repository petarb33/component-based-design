package com.f1.customer_service.feign;

import com.f1.customer_service.dto.OrderDTO;
import jakarta.validation.constraints.Min;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("tickets")
public interface TicketProxy {
    @GetMapping("/api/orders/customers/{customerId}")
    List<OrderDTO> getOrdersFromCustomer(@PathVariable @Min(1) Integer customerId);
}
