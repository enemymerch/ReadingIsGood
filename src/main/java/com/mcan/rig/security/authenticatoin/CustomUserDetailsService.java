package com.mcan.rig.security.authenticatoin;

import com.mcan.rig.persistance.entity.Customer;
import com.mcan.rig.service.CustomerService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerService customerService;

    public CustomUserDetailsService (CustomerService customerService) {this.customerService = customerService;}

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        Customer customer = customerService.findByUsername(username);
        return new CustomUserDetails(customer);
    }
}
