package com.f1.tickets_service.service;

import com.f1.tickets_service.dto.RaceDTO;
import com.f1.tickets_service.dto.CustomerDTO;
import com.f1.tickets_service.dto.OrderDTO;
import com.f1.tickets_service.dto.OrderItemDTO;
import com.f1.tickets_service.exception.EntityAlreadyExistsException;
import com.f1.tickets_service.exception.EntityDoesNotExistException;
import com.f1.tickets_service.dto.TicketDTO;
import com.f1.tickets_service.exception.InvalidDateException;
import com.f1.tickets_service.exception.PriceNotValidException;
import com.f1.tickets_service.feign.CustomerProxy;
import com.f1.tickets_service.feign.RacesProxy;
import com.f1.tickets_service.model.*;
import com.f1.tickets_service.repository.*;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketsService {

    private final TicketRepository ticketRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final CustomerProxy customerProxy;
    private final RacesProxy racesProxy;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket createTicket(TicketDTO dto) {
        Ticket ticket = new Ticket();
        ticket.setCategory(dto.getCategory());
        ticket.setType(dto.getType());
        ticket.setSector(dto.getSector());
        ticket.setPrice(dto.getPrice());
        RaceDTO raceDto = racesProxy.getRaceById(dto.getRaceId());
        ticket.setRaceId(raceDto.getId());

        return ticketRepository.save(ticket);
    }

    public Ticket getTicketById(Integer id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("Ticket with id " + id + " does not exist"));
    }

    @Transactional
    public void deleteTicketsByRaceId(Integer raceId){
        List<Ticket> tickets = ticketRepository.findAllByRaceId(raceId);
        for (Ticket ticket : tickets) {
            orderItemRepository.deleteByTicketId(ticket.getId());
        }
        ticketRepository.deleteByRaceId(raceId);
    }

    public Ticket updateTicketPrice(@PathVariable @Min(1) Integer idTicket, Integer price) {
        Ticket ticket = ticketRepository.findById(idTicket).
                orElseThrow(() -> new EntityDoesNotExistException("Ticket with id " + idTicket + " not found"));

        if(price < 0){
            throw new PriceNotValidException("Price can't be negative");
        }
        BigDecimal bd = new BigDecimal(price);
        if (bd.compareTo(ticket.getPrice()) == 0) {
            throw new PriceNotValidException("Price can't be the same");
        }

        ticket.setPrice(bd);
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Integer idTicket) {
        Ticket ticket = ticketRepository.findById(idTicket)
                .orElseThrow(() -> new EntityDoesNotExistException("Ticket with id " + idTicket + " not found"));

        ticketRepository.delete(ticket);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Integer id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("Order with id " + id + " not found"));
    }

    public Order addOrder(OrderDTO dto) {
        CustomerDTO customerDTO = customerProxy.getCustomer(dto.getCustomer_id());
        Order order = new Order();
        order.setCustomerId(customerDTO.getId());
        order.setOrderDate(dto.getOrder_date());
        order.setStatus(String.valueOf(dto.getStatus()));
        return orderRepository.save(order);
    }

    public void deleteOrders(Integer id) {
        Order order =  orderRepository.findById(id).
                orElseThrow(() -> new EntityDoesNotExistException("Order not found"));

        List<OrderItem> orderItemList = orderItemRepository.findAllByOrder(order);

        orderItemRepository.deleteAll(orderItemList);

        orderRepository.delete(order);
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem getOrderItemById(Integer id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("OrderItem with id " + id + " not found"));
    }

    public OrderItem addOrderItem(OrderItemDTO dto) {
        Order order = orderRepository.findById(dto.getOrder_id())
                .orElseThrow(() -> new EntityDoesNotExistException("Order not found"));

        Ticket ticket = ticketRepository.findById(dto.getTicket_id())
                .orElseThrow(() -> new EntityDoesNotExistException("Ticket not found"));

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setTicket(ticket);
        orderItem.setQuantity(dto.getQuantity());
        BigDecimal totalPrice = ticket.getPrice().multiply(BigDecimal.valueOf(dto.getQuantity()));
        orderItem.setTotalPrice(totalPrice);
        return orderItemRepository.save(orderItem);

    }

    public void deleteOrderItem(Integer id){
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("OrderItem not found"));

        orderItemRepository.delete(orderItem);
    }


    public List<Order> getOrdersFromCustomer(Integer customerId) {
        CustomerDTO customerDTO = customerProxy.getCustomer(customerId);
        return orderRepository.findAllByCustomerId(customerId);
    }

    public List<RaceDTO>  getRacesFromCustomer(Integer customerId) {
        CustomerDTO customerDTO = customerProxy.getCustomer(customerId);

        List<Integer> raceIdList = orderItemRepository.findRaceIdsByCustomerId(customerId);

        List<RaceDTO> races = new ArrayList<>();
        for(Integer raceId : raceIdList){
            RaceDTO raceDto = racesProxy.getRaceById(raceId);
            races.add(raceDto);
        }

        return races;
    }

}
