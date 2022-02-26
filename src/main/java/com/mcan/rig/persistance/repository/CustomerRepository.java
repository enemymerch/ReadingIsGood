package com.mcan.rig.persistance.repository;

import com.mcan.rig.persistance.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername (String username);

    boolean existsByUsername(String username);

}
