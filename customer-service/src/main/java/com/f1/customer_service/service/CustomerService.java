package com.f1.customer_service.service;

import com.f1.customer_service.dto.OrderDTO;
import com.f1.customer_service.exception.EntityDoesNotExistException;
import com.f1.customer_service.feign.TicketProxy;
import com.f1.customer_service.model.Customer;
import com.f1.customer_service.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    TicketProxy ticketProxy;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("Customer with id " + id + " does not exist"));
    }

    public List<OrderDTO> getOrdersFromCustomer(Integer customerId) {
        return ticketProxy.getOrdersFromCustomer(customerId);
    }

}
