package com.f1.tickets_service.repository;

import com.f1.tickets_service.model.RaceWeekend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RaceWeekendRepository extends JpaRepository<RaceWeekend, Integer> {
    Optional<RaceWeekend> findByName(String fullName);
}
