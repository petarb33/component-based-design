package com.f1.tickets_service.controller;

import com.f1.tickets_service.dto.OrderDTO;
import com.f1.tickets_service.dto.OrderItemDTO;
import com.f1.tickets_service.dto.RaceDTO;
import com.f1.tickets_service.dto.TicketDTO;
import com.f1.tickets_service.model.*;
import com.f1.tickets_service.service.TicketsService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TicketsController {

    private final TicketsService ticketsService;

    @GetMapping("/tickets")
    @ResponseStatus(HttpStatus.OK)
    public List<Ticket> findAllTickets() {
        return ticketsService.getAllTickets();
    }

    @GetMapping("/tickets/{id}")
    public Ticket getTicketById(@PathVariable @Min(1) Integer id) {
        return ticketsService.getTicketById(id);
    }

    @PostMapping("/tickets")
    public ResponseEntity<Ticket> createTicket(@RequestBody @Valid TicketDTO ticketDto) {
        return new ResponseEntity<>(ticketsService.createTicket(ticketDto), HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/tickets/race/{raceId}")
    public void deleteTickets(@PathVariable @Min(1) Integer raceId) {
        ticketsService.deleteTicketsByRaceId(raceId);
    }

    @DeleteMapping("/tickets/{id}")
    public void deleteTicket(@PathVariable @Min(1) Integer id) {
        ticketsService.deleteTicket(id);
    }

    @PatchMapping("/tickets/{idTicket}/price/{price}")
    public Ticket updateTicketPrice(@PathVariable @Min(1) Integer idTicket, @PathVariable Integer price){
        return ticketsService.updateTicketPrice(idTicket, price);
    }

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> findAllOrders() {
        return ticketsService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable @Min(1) Integer id) {
        return ticketsService.getOrderById(id);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderDTO orderDto) {
        return new ResponseEntity<>(ticketsService.addOrder(orderDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/orders/{id}")
    public void deleteOrders(@PathVariable @Min(1) Integer id) {
        ticketsService.deleteOrders(id);
    }

    @GetMapping("/orderItems")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderItem> findAllOrderItems() {
        return ticketsService.getAllOrderItems();
    }

    @GetMapping("/orderItems/{id}")
    public OrderItem getOrderItemById(@PathVariable @Min(1) Integer id) {
        return ticketsService.getOrderItemById(id);
    }

    @PostMapping("/orderItems")
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItemDTO orderItemDto) {
        return new ResponseEntity<>(ticketsService.addOrderItem(orderItemDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/orderItems/{id}")
    public void deleteOrderItem(@PathVariable @Min(1) Integer id) {
        ticketsService.deleteOrderItem(id);
    }

    @GetMapping("/orders/customers/{customerId}")
    public List<Order> getOrdersFromCustomer(@PathVariable @Min(1) Integer customerId){
        return ticketsService.getOrdersFromCustomer(customerId);
    }

    @GetMapping("/races/customers/{customerId}")
    public List<RaceDTO> getRaces(@PathVariable @Min(1) Integer customerId){
        return ticketsService.getRacesFromCustomer(customerId);
    }
}
