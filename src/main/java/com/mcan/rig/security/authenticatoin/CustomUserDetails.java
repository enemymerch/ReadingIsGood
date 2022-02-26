package com.mcan.rig.security.authenticatoin;

import com.mcan.rig.persistance.entity.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final Customer customer;

    public CustomUserDetails (Customer customer) {this.customer = customer;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(customer.getRole().getValue());
        authorities.add(authority);
        return authorities;
    }

    @Override
    public String getPassword () {
        return customer.getPassword();
    }

    @Override
    public String getUsername () {
        return customer.getUsername();
    }

    @Override
    public boolean isAccountNonExpired () {
        return true;
    }

    @Override
    public boolean isAccountNonLocked () {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired () {
        return true;
    }

    @Override
    public boolean isEnabled () {
        return true;
    }
}
