package com.f1.tickets_service.repository;

import com.f1.tickets_service.model.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {

    List<Ticket> findAllByRaceId(Integer raceId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Ticket t WHERE t.raceId = :raceId")
    void deleteByRaceId(@Param("raceId") Integer raceId);
}
