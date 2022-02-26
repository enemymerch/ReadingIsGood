package com.mcan.rig.service;

import com.mcan.rig.persistance.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findALl ();

    Customer findByUsername (String username);

    Customer create (Customer customer);
}
