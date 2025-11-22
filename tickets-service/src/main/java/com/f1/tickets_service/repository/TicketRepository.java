package com.f1.tickets_service.repository;

import com.f1.tickets_service.model.RaceWeekend;
import com.f1.tickets_service.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {

    List<Ticket> findAllByRaceWeekendId(Integer raceWeekendId);
}
