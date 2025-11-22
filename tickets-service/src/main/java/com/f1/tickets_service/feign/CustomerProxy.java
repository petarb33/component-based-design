package com.f1.tickets_service.feign;

import com.f1.tickets_service.dto.CustomerDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("customers")
public interface CustomerProxy {
    @GetMapping("/api/customers/{customerId}")
    CustomerDTO getCustomer(@PathVariable @Min(1) Integer customerId);
}
