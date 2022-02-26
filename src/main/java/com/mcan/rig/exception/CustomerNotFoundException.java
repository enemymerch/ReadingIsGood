package com.mcan.rig.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException (String identifier) {
        super("Customer not found with identifier: " + identifier);
    }
}
