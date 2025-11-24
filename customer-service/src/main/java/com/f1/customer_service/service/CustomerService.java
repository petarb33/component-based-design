package com.f1.customer_service.service;

import com.f1.customer_service.dto.CustomerAddressDTO;
import com.f1.customer_service.dto.OrderDTO;
import com.f1.customer_service.exception.EntityDoesNotExistException;
import com.f1.customer_service.feign.TicketProxy;
import com.f1.customer_service.model.Customer;
import com.f1.customer_service.model.CustomerAddress;
import com.f1.customer_service.repository.CustomerAddressRepository;
import com.f1.customer_service.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerAddressRepository customerAddressRepository;
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

    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findById(id)
                        .orElseThrow(() -> new EntityDoesNotExistException("Customer with id " + id + " does not exist"));

        List<CustomerAddress> addresses = customerAddressRepository.findByCustomerId(id);
        customerAddressRepository.deleteAll(addresses);

        customerRepository.delete(customer);
    }

    public List<CustomerAddress> getAllAddresses() {
        return customerAddressRepository.findAll();
    }

    public CustomerAddress findAddressById(Integer id) {
        return customerAddressRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("Address with id " + id + " does not exist"));
    }

    public List<CustomerAddress> findAddressesByCustomerId(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityDoesNotExistException("Customer with id " + customerId + " does not exist"));

        return customerAddressRepository.findByCustomerId(customerId);
    }

    public CustomerAddress addCustomerAddress(CustomerAddressDTO dto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(dto.getCustomerId());
        if(optionalCustomer.isEmpty()) {
            throw new EntityDoesNotExistException("Customer with id " + dto.getCustomerId() + " does not exist");
        }

        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setCustomer(optionalCustomer.get());
        customerAddress.setCountry(dto.getCountry());
        customerAddress.setCity(dto.getCity());
        customerAddress.setStreet(dto.getStreet());
        customerAddress.setType(dto.getType());
        customerAddress.setPostalCode(dto.getPostalCode());

        return customerAddressRepository.save(customerAddress);
    }

    public void deleteAddress(Integer id){
        CustomerAddress customerAddress = customerAddressRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("Address with id " + id + " does not exist"));
        customerAddressRepository.delete(customerAddress);
    }

}
