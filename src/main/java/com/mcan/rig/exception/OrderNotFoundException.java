package com.mcan.rig.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException (Long identifier) {
        super("Customer not found with identifier: " + identifier);
    }
}
