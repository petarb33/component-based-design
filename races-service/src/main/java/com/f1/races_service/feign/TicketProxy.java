package com.f1.races_service.feign;

import jakarta.validation.constraints.Min;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("tickets")
public interface TicketProxy {
    @DeleteMapping("/api/tickets/race/{raceId}")
    void  deleteTickets(@PathVariable @Min(1) Integer raceId);
}
