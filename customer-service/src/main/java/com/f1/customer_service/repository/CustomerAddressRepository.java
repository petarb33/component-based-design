package com.f1.customer_service.repository;

import com.f1.customer_service.model.Customer;
import com.f1.customer_service.model.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer> {
    List<CustomerAddress> findByCustomerId(Integer customerId);

    List<CustomerAddress> customer(Customer customer);
}
