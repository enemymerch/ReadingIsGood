package com.mcan.rig.service;

import com.mcan.rig.persistance.entity.Customer;
import com.mcan.rig.persistance.repository.CustomerRepository;
import com.mcan.rig.service.impl.CustomerServiceImpl;
import com.mcan.rig.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerRepository customerRepository;


    private Customer customer;

    @BeforeEach
    public void initObjects () {
        // customer
        customer = new Customer();
        customer.setCreationDate(new Date());
        customer.setFirstName("Dave");
        customer.setLastName("Klark");
        customer.setUsername("dave");
        customer.setPassword("dave");
        customer.setRole(UserRole.CUSTOMER);
        customer.setId(1141L);
    }

    @Test
    void findByUsernameTest () {
        String username = customer.getUsername();
        Mockito.when(customerRepository.findByUsername(username)).thenReturn(Optional.ofNullable(customer));
        Customer result = customerService.findByUsername(username);
        assertEquals(result, customer);
    }


    @Test
    void create () {
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Customer result = customerService.create(customer);
        assertEquals(result, customer);
    }

}
