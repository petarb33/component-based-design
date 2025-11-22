package com.f1.tickets_service.repository;

import com.f1.tickets_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByCustomerId(Integer customerId);
}
