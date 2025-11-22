package com.f1.customer_service.controller;

import com.f1.customer_service.dto.OrderDTO;
import com.f1.customer_service.model.Customer;
import com.f1.customer_service.service.CustomerService;
import feign.Response;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> findAll() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/customers/add")
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable @Min(1) Integer id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/customers/{id}/orders")
    public List<OrderDTO> getOrdersFromCustomer(@PathVariable @Min(1) Integer id) {
        return customerService.getOrdersFromCustomer(id);
    }

}
