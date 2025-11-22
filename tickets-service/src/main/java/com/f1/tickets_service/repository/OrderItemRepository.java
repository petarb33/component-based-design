package com.f1.tickets_service.repository;

import com.f1.tickets_service.model.Order;
import com.f1.tickets_service.model.OrderItem;
import com.f1.tickets_service.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
    List<OrderItem> findAllByTicket(Ticket ticket);

    List<OrderItem> findAllByOrder(Order order);
}
