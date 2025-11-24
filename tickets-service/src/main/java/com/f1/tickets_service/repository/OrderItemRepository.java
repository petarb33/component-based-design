package com.f1.tickets_service.repository;

import com.f1.tickets_service.model.Order;
import com.f1.tickets_service.model.OrderItem;
import com.f1.tickets_service.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findAllByTicket(Ticket ticket);

    List<OrderItem> findAllByOrder(Order order);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.customerId = :customerId")
    List<OrderItem> findByCustomerId(@Param("customerId") Integer customerId);

    @Query("SELECT oi.ticket.raceId FROM OrderItem oi WHERE oi.order.customerId = :customerId AND oi.order.status = 'CONFIRMED'")
    List<Integer> findRaceIdsByCustomerId(@Param("customerId") Integer customerId);

    @Modifying
    @Query("DELETE FROM OrderItem oi WHERE oi.ticket.id = :ticketId")
    void deleteByTicketId(Integer ticketId);
}
