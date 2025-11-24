package com.f1.tickets_service.feign;

import com.f1.tickets_service.dto.RaceDTO;
import jakarta.validation.constraints.Min;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("races")
public interface RacesProxy {
    @GetMapping("api/races/{raceId}")
    RaceDTO getRaceById(@PathVariable @Min(1) Integer raceId);
}
