package com.mcan.rig.service.impl;

import com.mcan.rig.enums.UserRole;
import com.mcan.rig.exception.CustomerBadCreateRequestBodyException;
import com.mcan.rig.exception.CustomerNotFoundException;
import com.mcan.rig.exception.UsernameAlreadyExitsException;
import com.mcan.rig.persistance.entity.Customer;
import com.mcan.rig.persistance.repository.CustomerRepository;
import com.mcan.rig.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findALl() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username).orElseThrow(() -> new CustomerNotFoundException(username));
    }

    @Override
    public Customer create(Customer customer) {
        if (customer == null ||
            customer.getUsername() == null ||
            customer.getFirstName() == null ||
            customer.getLastName() == null ||
            customer.getPassword() == null) {
            throw new CustomerBadCreateRequestBodyException();
        }

        if (customerRepository.existsByUsername(customer.getUsername())) {
            throw new UsernameAlreadyExitsException();
        }
        customer.setRole(UserRole.CUSTOMER);

        return customerRepository.save(customer);
    }
}
