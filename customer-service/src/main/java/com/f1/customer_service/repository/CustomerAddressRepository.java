package com.f1.customer_service.repository;

import com.f1.customer_service.model.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer> {
}
